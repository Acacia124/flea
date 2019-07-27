import Dialog from '../../vantui/dist/dialog/dialog';
var api = require('../../config/api.js');

// pages/msg-list/msg-list.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    sysMsgList: [],
    chatMsgList: [],
    sysSum: 0
  },

  // 查看信息
  viewMsg: function(e) {
    var that = this;
    var token = wx.getStorageSync('Authorization');
    if (!token) {
      alert('请重新登录');
    } else {
      wx.request({
        url: api.MsgSeleft,
        data: {
          messageInfoId: that.data.sysMsgList[e.currentTarget.dataset.id].id
        },
        header: {
          'content-type': 'application/json',
          'Authorization': token // 缓存中token信息
        },
        success: function(res) {
          Dialog.alert({
            title: res.data.data.messageTitle,
            message: res.data.data.messageTxt,
            confirmButtonText: '已阅',
            showCancelButton: true,
            cancelButtonText: '删除'
          }).then(() => {
            var msgList = that.data.sysMsgList;
            if (msgList[e.currentTarget.dataset.id].status) {} else {
              msgList[e.currentTarget.dataset.id].status = true;
              that.setData({
                sysMsgList: msgList,
                sysSum: that.data.sysSum - 1
              })
            }
          }).catch(() => {
            var msgList = that.data.sysMsgList;
            if (msgList[e.currentTarget.dataset.id].status) {} else {
              that.setData({
                sysSum: that.data.sysSum - 1
              })
            }
            msgList.splice(e.currentTarget.dataset.id, 1);
            that.setData({
              sysMsgList: msgList
            })
          });;
        }
      });
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    // 获取缓存加入chatMsgList
    const value = wx.getStorageSync(api.IM);
    if (value) {
      that.setData({
        chatMsgList: JSON.parse(value)
      })
    }
    // 获取缓存加入sysMsgList
    const value2 = wx.getStorageSync(api.MSG);
    if (value2) {
      that.setData({
        sysMsgList: JSON.parse(value2)
      })
    }
    that.getSysMsg();
    that.getChatMsg();
  },

  // 获取站内信未读信息
  getSysMsg: function() {
    var that = this;
    var token = wx.getStorageSync('Authorization');
    if (!token) {
      alert('请重新登录');
    } else {
      wx.request({
        url: api.MsgNoRead,
        data: '',
        header: {
          'content-type': 'application/json',
          'Authorization': token // 缓存中token信息
        },
        success: function(res) {
          if (res.data.status === 200) {
            var msgList = that.data.sysMsgList;
            var sum = res.data.data.length;
            if (msgList == null || msgList.length <= 0) {
              msgList = res.data.data
            } else {
              if (res.data.data == null || res.data.data.length <= 0) {} else {
                for (var i = 0; i < res.data.data.length; i++) {
                  for (var j = 0; j < msgList.length; j++) {
                    if (msgList[j].id == res.data.data[i].id) {
                      break;
                    } else if (j == msgList.length - 1) {
                      msgList.push(res.data.data[i]);
                    }
                  }
                }
              }
            }
            that.setData({
              sysMsgList: msgList,
              sysSum: sum
            })
          }
        }
      });
    }
  },

  // 获取聊天未读列表
  getChatMsg: function() {
    var that = this;
    var token = wx.getStorageSync('Authorization');
    if (!token) {
      alert('请重新授权登录');
    } else {
      wx.request({
        url: api.WsList,
        data: '',
        header: {
          'content-type': 'application/json',
          'Authorization': token // 缓存中token信息
        },
        success: function(res) {
          if (res.data.status == 200) {
            var chatMsgList = that.data.chatMsgList;
            if (chatMsgList == null || chatMsgList.length <= 0) {
              chatMsgList = res.data.data
            } else {
              if (res.data.data == null || res.data.data.length <= 0) {} else {
                // 整合两个元素
                let userList = [];
                for (let i = 0; i < chatMsgList.length; i++) {
                  for (let j = 0; j < res.data.data.length; j++) {
                    if (chatMsgList[i].openId === res.data.data[j].openId) {
                      userList.push(res.data.data[j]);
                      break;
                    }
                    if (j === res.data.data.length - 1) {
                      userList.push(chatMsgList[i]);
                    }
                  }
                }
                for (let j = 0; j < res.data.data.length; j++) {
                  for (let i = 0; i < chatMsgList.length; i++) {
                    if (chatMsgList[i].openId === res.data.data[j].openId) {
                      break;
                    }
                    if (i === chatMsgList.length - 1) {
                      userList.push(res.data.data[j]);
                    }
                  }
                }
                chatMsgList = userList;
              }
            }
            that.setData({
              chatMsgList: chatMsgList
            })
          }
        }
      });
    }
  },

  // 进入聊天
  toChatPage: function(e) {
    var that = this;
    var index = e.currentTarget.dataset.index;
    wx.navigateTo({
      url: '../chat/chat?openid=' + that.data.chatMsgList[index].openId + '&avatarUrl=' + that.data.chatMsgList[index].imgUrl,
      success: function() {
        var t = that.data.chatMsgList;
        t[index].msgNum = 0;
        that.setData({
          chatMsgList: t
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function() {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function() {
    try {
      wx.setStorageSync(api.MSG, JSON.stringify(this.data.sysMsgList));
      wx.setStorageSync(api.IM, JSON.stringify(this.data.chatMsgList));
    } catch (e) {}
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function() {
    try {
      wx.setStorageSync(api.MSG, JSON.stringify(this.data.sysMsgList));
      wx.setStorageSync(api.IM, JSON.stringify(this.data.chatMsgList));
    } catch (e) {}
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

  },


})