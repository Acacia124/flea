// pages/contact/contact.js
var util = require('../../utils/util.js')
const app = getApp();
var api = require('../../config/api.js');
var inputVal = '';
var msgList = [];
var windowWidth = wx.getSystemInfoSync().windowWidth;
var windowHeight = wx.getSystemInfoSync().windowHeight;
var keyHeight = 0;
var SocketTask;
var socketOpen = false;

Page({

  /**
   * 页面的初始数据
   */
  data: {
    scrollHeight: '100vh',
    inputBottom: 0,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.initProduct(options);
    that.initLocalHostHis();
    that.setData({
      cusHeadIcon: app.globalData.userInfo.avatarUrl,
    });
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this;
    if (!socketOpen) {
      that.webSocket()
    }
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
   * 连接websocket
   *  
   */
  webSocket: function() {
    var that = this;
    var token = wx.getStorageSync(api.HeadToken);
    if (!token) {
      wx.showToast({
        title: "请授权登录后发布",
        image: '../../images/hint1.png',
        mask: true,
      })
      setTimeout(function() {
        wx.navigateTo({
          url: '../mine/mine',
        })
      }, 1000)
    } else {
      wx.request({
        url: api.CheckUser,
        header: {
          'content-type': 'application/json',
          'Authorization': token // 缓存中token信息
        },
        success: function(res) {
          if (res.data.status == 200) {
            that.setData({
              myOpenId: res.data.data
            })
            // 连接websocket
            SocketTask = wx.connectSocket({
              url: api.WsIM + res.data.data + '/' + that.data.itemInfo.openid,
              header: {
                'content-type': 'application/json'
              },
              method: 'GET',
              success: function(res) {},
              fail: function(err) {
                wx.showToast({
                  title: '网络异常！',
                })
                console.log(err)
              },
            })
            that.initWebsocket();
          } else if (res.data.status == 402) {
            wx.showToast({
              title: res.data.msg,
              image: '../../images/hint2.png',
              mask: true,
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
            }, 1000)
          }
        }
      })
    }
  },
  /**初始化websocket */
  initWebsocket: function() {
    var that = this;
    SocketTask.onOpen(res => {
      socketOpen = true;
      console.log('监听 WebSocket 连接打开事件。', res)
    })
    SocketTask.onClose(onClose => {
      console.log('监听 WebSocket 连接关闭事件。', onClose)
      socketOpen = false;
      wx.setStorageSync(api.IM_Prefix + that.data.itemInfo.openid, JSON.stringify(msgList))
    })
    SocketTask.onError(onError => {
      console.log('监听 WebSocket 错误。错误信息', onError)
      socketOpen = false
    })
    SocketTask.onMessage(onMessage => {
      var msg = JSON.parse(onMessage.data);
      if (msg.status != null) {
        // 历史记录
        msgList = util.fomartMsgList(msgList.concat(msg.data));
        that.setData({
          msgList,
          his_index: msgList.length-1,
          today: util.toWeiXinString(new Date())
        });
      } else {
        // 非历史记录
        msg.createTime = new Date();
        msgList.push(formatNewMsg(msg));
        that.setData({
          msgList
        })
      }
      that.setData({
        toView: 'msg-' + (msgList.length - 1)
      })
    })
  },

  // 加载本地聊天记录
  initLocalHostHis: function() {
    var that = this;
    const his = wx.getStorageSync(api.IM_Prefix + that.data.itemInfo.openid);
    if (his == null || his == '') {
      return;
    }
    msgList = JSON.parse(his);
    inputVal = '';
    that.setData({
      msgList,
      inputVal
    })
  },
  // 获取商家信息
  initProduct: function(options) {
    var that = this;
    that.setData({
      itemInfo: options
    })
  },

  /**
   * 获取聚焦
   */
  focus: function(e) {
    keyHeight = e.detail.height;
    this.setData({
      scrollHeight: (windowHeight - keyHeight) + 'px'
    });
    this.setData({
      toView: 'msg-' + (msgList.length - 1),
      inputBottom: keyHeight + 'px'
    })
  },

  //失去聚焦(软键盘消失)
  blur: function(e) {
    this.setData({
      scrollHeight: '100vh',
      inputBottom: 0
    })
    this.setData({
      toView: 'msg-' + (msgList.length - 1)
    })

  },

  /**
   * 发送点击监听
   */
  sendClick: function(e) {
    let that = this;
    var data = e.detail.value;
    if(data.trim()=='') {
      return;
    }
    if (!socketOpen) {
      that.webSocket()
    }
    var msg = {
      msgType: 1,
      content: data
    };
    sendSocketMessage(JSON.stringify(msg));

    msgList.push(formatNewMsg({
      msgType: 1,
      sendTo: that.data.itemInfo.openid,
      createBy: that.data.myOpenId,
      createTime: new Date(),
      delFlag: 1,
      isOffLine: 1,
      content: data
    }));
    inputVal = '';
    this.setData({
      msgList,
      inputVal,
      toView: 'msg-' + (msgList.length - 1),
    })
  },

  // 发送商品信息
  sendItem: function () {
    let that = this;
    if (!socketOpen) {
      that.webSocket()
    }
    var msg = {
      msgType: 3,
      content: that.data.itemInfo.id + '_#_' + that.data.itemInfo.img + '_#_' + that.data.itemInfo.title
    };
    sendSocketMessage(JSON.stringify(msg));

    msgList.push(formatNewMsg({
      msgType: 3,
      sendTo: that.data.itemInfo.openid,
      createBy: that.data.myOpenId,
      createTime: new Date(),
      delFlag: 1,
      isOffLine: 1,
      content: that.data.itemInfo.id + '_#_' + that.data.itemInfo.img + '_#_' + that.data.itemInfo.title
    }));
    this.setData({
      msgList,
      toView: 'msg-' + (msgList.length - 1),
    })
  },

  /**
   * 退回上一页
   */
  toBackClick: function() {
    wx.navigateBack({})
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {
    SocketTask.close(function(close) {})
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {
    SocketTask.close(function(close) {})
  },

  //预览图片
  // previewImage: function (e) {
  //   var current = e.target.dataset.src;
  //   wx.previewImage({
  //     current: current, // 当前显示图片的http链接  
  //     urls: [current] // 需要预览的图片http链接列表  
  //   })
  // },
})

function sendSocketMessage(msg) {
  SocketTask.send({
    data: msg
  })
}

function formatNewMsg(msg) {
  if (msgList != null && msgList.length > 0) {
    let createTimeFirst = new Date(msg.createTime).getTime();
    let createTimeSecond = new Date(msgList[msgList.length - 1].createTime).getTime();
    let minute = (createTimeFirst - createTimeSecond) / 1000 / 60;
    if (minute > 5) {
      msg.format = true;
      msg.time = util.toWeiXinString(new Date(msg.createTime));
    }
  } else {
    msg.format = true;
    msg.time = util.toWeiXinString(new Date(msg.createTime));
  }
  return msg;
}