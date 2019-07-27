// pages/itemIssue/itemIssue.js
var network = require('../../utils/network.js')
var util = require('../../utils/util.js')
var api = require('../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    newDate: "",
    catId: 1,
    catText: "",
    image: "",
    newness_rate: 70,
    buy_tiem: "2018-09-01",
    consignment_address: [],
    consignment_addressIndex: 0,
    status: ["普通出售", "无偿捐赠"],
    statusIndex: 0,
    isAgree: false,
    price: null,
    //图片
    list: '',
    free: false,
    upload_picture_list: []
  },

  //获取地址列表
  getSchoolAddress: function() {
    var that = this;
    network.request({
      url: api.SchoolList,
      success: function(res) {
        that.setData({
          consignment_address: res.data
        })
      },
      fail: function(error) {
        wx.showToast({
          title: '检查你的网络',
          image: '../../images/hint1.png',
          mask: true,
        })
      }
    })
  },

  //选择分类
  chooseCat: function() {
    wx.navigateTo({
      url: '../itemCat/itemCat',
    })
  },
  //拼接imageurl
  jointImageUrl: function() {
    var that = this;
    console.log(that.data.upload_picture_list);
    for (var i = 0; i < that.data.upload_picture_list.length; i++) {
      if (that.data.image !== "") {
        var image = that.data.image + "," + that.data.upload_picture_list[i].path_server;
      } else {
        var image = that.data.upload_picture_list[i].path_server;
      }
      that.setData({
        image: image
      })
    };
  },

  //选择图片方法
  uploadpic: function(e) {
    var that = this //获取上下文
    var upload_picture_list = that.data.upload_picture_list
    //选择图片
    wx.chooseImage({
      count: 8,
      sizeType: ['compressed'],
      sourceType: ['album', 'camera'],
      success: function(res) {
        var tempFiles = res.tempFiles
        //把选择的图片 添加到集合里
        for (var i in tempFiles) {
          tempFiles[i]['upload_percent'] = 0
          tempFiles[i]['path_server'] = ''
          upload_picture_list.push(tempFiles[i])
        }
        //显示
        that.setData({
          upload_picture_list: upload_picture_list,
        });
      }
    })
  },
  //点击上传事件
  uploadimage: function() {
    var page = this
    var token = wx.getStorageSync(api.HeadToken);
    if (token) {
      var upload_picture_list = page.data.upload_picture_list
      //循环把图片上传到服务器 并显示进度       
      for (var j in upload_picture_list) {
        if (upload_picture_list[j]['upload_percent'] == 0) {
          //调用函数
          var url = api.UploadFile;
          util.upload_file_server(url, page, upload_picture_list, j,token)
        }
      }
  } else {
    wx.showToast({
      title: '请登录后上传',
      image: '../../images/hint1.png',
      mask: true,
    })
  }
},

// 删除图片
deleteImg: function(e) {
  let upload_picture_list = this.data.upload_picture_list;
  let index = e.currentTarget.dataset.index;
  upload_picture_list.splice(index, 1);
  this.setData({
    upload_picture_list: upload_picture_list
  });
},

//发布提交
formSubmit: function(e) {
  var that = this;
  that.jointImageUrl();

  var item = e.detail.value;
  if (item.title == "" || item.sellPoint == "" || item.price == "" || item.originalPrice == "" || ((item.qq == "") && (item.phone == "") && (item.wechat == ""))) {
    wx.showToast({
      title: "表单填写不完整，请检查",
      image: '../../images/hint2.png',
      mask: true,
    })
  } else {
    if (wx.getStorageSync(api.HeadToken)) {
      var token = wx.getStorageSync(api.HeadToken);
      var schoolName = that.data.consignment_address[item.consignmentAddress].schoolName;
      console.log(schoolName);
      wx.showLoading({
        title: '加载中',
      })
      wx.request({
        url: api.SaveProduct,
        header: {
          'content-type': 'application/x-www-form-urlencoded',
          'Authorization': token
        },
        method: 'POST',
        data: {
          itemImage: that.data.image,
          catId: that.data.catId,
          itemTitle: item.title,
          sellPoint: item.sellPoint,
          price: item.price * 100,
          buyTem: item.buyTiem,
          originalPrice: item.originalPrice * 100,
          sellAddress: schoolName,
          sellQq: item.qq,
          sellPhone: item.phone,
          sellWechat: item.wechat,
          newnessRate: item.newnessRate,
          status: item.status
        },
        success: function(res) {
          console.log(res.data);
          wx.hideLoading();
          if (res.data.status === 200) {
            that.setData({
              image: ""
            })
            wx.showToast({
              title: '发布成功',
              icon: 'success',
              mask: true,
              duration: 2000
            })
            setTimeout(function() {
              wx.reLaunch({
                url: '../index/index',
              })
            }, 2000)
          } else {
            wx.showToast({
              title: res.data.msg,
              image: '../../images/hint1.png',
              mask: true,
            })
          }
        },
        fail: function() {
          wx.hideLoading();
          wx.showToast({
            title: "当前通道拥堵，请稍后再试",
            image: '../../images/hint1.png',
            mask: true,
          })
        }
      })
    } else {
      wx.showToast({
        title: "请授权登录后发布",
        image: '../../images/hint1.png',
        mask: true,
      })
      setTimeout(function() {
        //要延时执行的代码
        wx.navigateTo({
          url: '../mine/mine',
        })
      }, 1000) //延迟时间 
    }

  }


},

//同步更新卖点
inputTypingDesc: function(e) {
  this.setData({
    sell_point: e.detail.value
  });
},

bindDateChange: function(e) {
  this.setData({
    buy_tiem: e.detail.value
  })
},

bindAddressChange: function(e) {
  this.setData({
    consignment_addressIndex: e.detail.value
  })
},
bindStatusChange: function(e) {
  var that = this;
  if (that.data.statusIndex == 0) {
    this.setData({
      statusIndex: e.detail.value,
      price: 0,
      free: true
    })
  } else {
    this.setData({
      statusIndex: e.detail.value,
      price: null,
      free: false
    })
  }

},
bindAgreeChange: function(e) {
  this.setData({
    isAgree: !!e.detail.value.length
  });
},
/**
 * 生命周期函数--监听页面加载
 */
onLoad: function(options) {
  var that = this;
  that.getSchoolAddress();
  var newDate = util.formatTime(new Date());
  //获取url传递的分类参数
  that.setData({
    newDate: newDate,
    catId: options.catId,
    catText: options.catText,
  })
},

/**
 * 生命周期函数--监听页面初次渲染完成
 */
onReady: function() {

},

/**
 * 生命周期函数--监听页面显示
 */
onShow: function() {

},

/**
 * 生命周期函数--监听页面隐藏
 */
onHide: function() {

},

/**
 * 生命周期函数--监听页面卸载
 */
onUnload: function() {

},

/**
 * 页面相关事件处理函数--监听用户下拉动作
 */
onPullDownRefresh: function() {

},

/**
 * 页面上拉触底事件的处理函数
 */
onReachBottom: function() {

},

/**
 * 用户点击右上角分享
 */
onShareAppMessage: function() {

}
})