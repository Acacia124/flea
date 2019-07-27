// pages/report/report.js
var api = require('../../config/api.js');
var network = require('../../utils/network.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    forList: [],
    isFirst: true,
    isLast: false,
    title: '',
    reportList: [{
        id: 100,
        title: '涉嫌欺诈',
        isLeaf: false,
        children: [{
            id: 110,
            title: '造成了金钱损失',
            isLeaf: false,
            children: [{
                id: 111,
                title: '在站外交货款/邮费/定金等，对方未发货',
                isLeaf: true,
              },
              {
                id: 112,
                title: '对方谎称视频验机，骗走我的钱',
                isLeaf: true,
              },
              {
                id: 113,
                title: '这些都不是',
                isLeaf: true,
              }
            ]
          },
          {
            id: 120,
            title: '未造成了金钱损失',
            isLeaf: false,
            children: [{
                id: 121,
                title: '对方让我提前付定金',
                isLeaf: true,
              },
              {
                id: 122,
                title: '对方给我发虚假链接',
                isLeaf: true,
              },
              {
                id: 123,
                title: '这些都不是',
                isLeaf: true,
              }
            ]
          }
        ]
      },
      {
        id: 200,
        title: '违规信息',
        isLeaf: false,
        children: [{
            id: 210,
            title: '枪支弹药、军警用品类',
            isLeaf: true
          },
          {
            id: 220,
            title: '假币、流通货币、银行卡及集资类',
            isLeaf: true
          },
          {
            id: 230,
            title: '易燃易爆、危险化学品、毒品类',
            isLeaf: true
          },
          {
            id: 240,
            title: '假证、假章等',
            isLeaf: true
          },
          {
            id: 250,
            title: '色青暴力类',
            isLeaf: true
          },
          {
            id: 260,
            title: '政治敏感类',
            isLeaf: true
          },
          {
            id: 270,
            title: '涉赌及相关产业类',
            isLeaf: true
          },
          {
            id: 280,
            title: '食品、药品、口服保健品',
            isLeaf: true
          },
          {
            id: 290,
            title: '这些都不是',
            isLeaf: true
          }
        ]
      },
      {
        id: 300,
        title: '广告骚扰',
        isLeaf: true
      },
      {
        id: 400,
        title: '辱骂骚扰',
        isLeaf: true
      },
      {
        id: 500,
        title: '其他',
        isLeaf: true
      },
    ]
  },
  changeCell: function(e) {
    var that = this;
    that.setData({
      forList: that.data.forList[e.currentTarget.dataset.id].children,
      isFirst: false
    })
  },
  returnCell: function() {
    var that = this;
    if (that.data.isLast) {
      that.setData({
        isLast: false
      })
    } else {
      var i = parseInt(that.data.forList[0].id / 100);
      var j = parseInt(((that.data.forList[0].id) - i * 100) / 10);
      var k = that.data.forList[0].id - i * 100 - j * 10;
      var flag = false;
      var list = [];
      if (k == 0) {
        flag = true;
        list = that.data.reportList
      } else {
        list = that.data.reportList[i - 1].children
      }
      that.setData({
        isFirst: flag,
        forList: list
      })

    }
  },
  toReport: function(e) {
    var that = this;
    var title = e.currentTarget.dataset.title;
    that.setData({
      isLast: true,
      title: title
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.setData({
      forList: that.data.reportList,
      itemInfo: options
    })
  },

  // 发送举报内容
  sendMsg: function() {
    var token = wx.getStorageSync('Authorization');
    console.log(this.data.itemInfo);
    if (!token) {
      wx.showToast({
        title: '请登陆后提交举报信息',
        image: '../../images/hint2.png',
        mask: true,
      });
    } else {
      const info = {
        messageTitle: '违规产品举报',
        messageTxt: '<h4>' + this.data.title + '</h4>'
          +'< span >' + this.data.content +'</span>'
          + '<p style="font-size: 15px">'
          + '商品名称：' + this.data.itemInfo.itemName + '( ' + this.data.itemInfo.itemId + ')</p>'
          + '发布人：' + this.data.itemInfo.userName + '( ' + this.data.itemInfo.userId + ')</p>',
        messageType: '消息'
      };
      let openIds = [];
      openIds.push(api.AdminId);
      network.request({
        url: api.MsgSend,
        method: 'POST',
        data: {
          openIds: JSON.stringify(openIds),
          info: JSON.stringify(info),
          isSystem: false
        },
        header: {
          'content-type': 'application/json', // 默认值
          'Authorization': token // 缓存中token信息
        },
        success: function(res) {
          if (res.data.status == 200) {
            wx.showToast({
              title: "您的举报我们将会尽快处理",
              image: '../../images/hint1.png',
              mask: true,
            })
          } else {
            wx.showToast({
              title: "举报失败！" + res.data.msg,
              image: '../../images/hint1.png',
              mask: true,
            })
          }
          setTimeout(function() {
            //要延时执行的代码
            wx.navigateBack({
              delta: 1
            });
          }, 1000)


        }
      })
    }
  },

  getContente: function(e) {
    var that = this;
    that.setData({
      content: e.detail
    })
  },

  getTitle: function(e) {
    var that = this;
    that.setData({
      title: e.detail
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