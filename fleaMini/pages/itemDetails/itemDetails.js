// pages/itemCat/itemCat.js
var network = require('../../utils/network.js')
var api = require('../../config/api.js');

Page({
  data: {
    isLike: false,
    //传入的商品Id
    itemId: 0,
    //轮播图
    imgUrls: [],
    //商品信息
    item: [],
    //商品发布人信息
    userInfo: [],
    indicatorDots: true, //是否显示面板指示点
    autoplay: true, //是否自动切换
    interval: 3000, //自动切换时间间隔,3s
    duration: 1000, //  滑动动画时长1s
    gender: "",

    // text:"这是一个页面"
    actionSheetHidden: true,
    actionSheetItems: [{
        bindtap: 'Menu1',
        txt: '',
        name: "QQ ",
        imageUrl: '../../images/qq.png'
      },
      {
        bindtap: 'Menu2',
        txt: '',
        name: "微信 ",
        imageUrl: '../../images/wechat.png'
      },
      {
        bindtap: 'Menu3',
        txt: '',
        name: "电话 ",
        imageUrl: '../../images/whatsapp.png'
      }
    ],
  },
  onLoad: function(options) {
    var that = this;
    that.setData({
      itemId: options.id
    })
    var token = wx.getStorageSync(api.HeadToken);
    network.request({
      url: api.ProductDetail,
      data: {
        itemId: that.data.itemId
      },
      header: {
        'content-type': 'application/json', // 默认值
        'Authorization': token // 缓存中token信息
      },
      success: function(res) {
        var gender = "";
        if (res.data.user.gender === 1) {
          gender = "男"
        } else {
          gender = "女"
        }
        var qq = 'actionSheetItems[0].txt';
        var wechat = 'actionSheetItems[1].txt';
        var phone = 'actionSheetItems[2].txt';
        that.setData({
          item: res.data.item,
          gender: gender,
          userInfo: res.data.user,
          imgUrls: res.data.item.itemImage.split(","),
          [qq]: res.data.item.sellQq,
          [wechat]: res.data.item.sellWechat,
          [phone]: res.data.item.sellPhone
        })
        that.getIsLove();
      },
      fail: function (error) {
        wx.showToast({
          title: '检查你的网络',
          image: '../../images/hint1.png',
          mask: true,
        })
      }
    })
  },
  //获取是否收藏
  getIsLove: function() {
   var that = this;
    var token = wx.getStorageSync(api.HeadToken);
    if (!token) {
    } else {
     wx.request({
       url: api.IsCollect,
       data: {
         itemId: that.data.itemId
       },
       header: {
         'content-type': 'application/json',
         'Authorization': token // 缓存中token信息
       },
       success: function (res) {
         if (res.data.status == 200) {
           that.setData({
             isLike: true
           });
         }
       }
     })
    }
  },
  //预览图片
  previewImage: function(e) {
    var current = e.target.dataset.src;

    wx.previewImage({
      current: current, // 当前显示图片的http链接  
      urls: this.data.imgUrls // 需要预览的图片http链接列表  
    })
  },
  // 收藏
  addLike() {
    var that = this;
    var token = wx.getStorageSync(api.HeadToken);
    if (!token){
     wx.showToast({
       title: "未登录不具有该权限",
       image: '../../images/hint1.png',
       mask: true,
     })
   } else {
      wx.request({
        url: api.UpdateCollect,
        data: {
          itemId: that.data.itemId
        },
        header: {
          'content-type': 'application/json',
          'Authorization': token // 缓存中token信息
        },
        success: function (res) {
          if (res.data.status==200){
            var i=0;
            if (that.data.isLike && that.data.item.collectNum!=0){
              i=-1;
            } else if (!that.data.isLike){
              i=1;
            }
            that.data.item.collectNum = that.data.item.collectNum+i;
            that.setData({
              isLike: !that.data.isLike,
              item: that.data.item
            });
          } else {
            wx.showToast({
              title: res.data.msg,
              image: '../../images/hint1.png',
              mask: true,
            })
          }
        }
      })
   }
  },

  toMyLove: function() {
    wx.navigateTo({
      url: '../mylove/mylove',
      success: function () {
      }
    })
  }
  // 联系卖家
  // actionSheetTap: function() {
  //   this.setData({
  //     actionSheetHidden: !this.data.actionSheetHidden
  //   })
  // },
  // actionSheetbindchange: function() {
  //   this.setData({
  //     actionSheetHidden: !this.data.actionSheetHidden
  //   })
  // },
  // bindMenu1: function() {
  //   this.setData({
  //     menu: 1,
  //     actionSheetHidden: !this.data.actionSheetHidden
  //   })
  // },
  // bindMenu2: function() {
  //   this.setData({
  //     menu: 2,
  //     actionSheetHidden: !this.data.actionSheetHidden
  //   })
  // },
  // bindMenu3: function() {
  //   this.setData({
  //     menu: 3,
  //     actionSheetHidden: !this.data.actionSheetHidden
  //   })
  //   wx.makePhoneCall({
  //     phoneNumber: this.data.actionSheetItems[2].txt 
  //   })
  // }
})