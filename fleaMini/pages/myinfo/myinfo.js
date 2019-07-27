var network = require('../../utils/network.js')
const app = getApp();
var api = require('../../config/api.js');

// pages/myinfo/myinfo.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    mailflag: false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    var token = wx.getStorageSync('Authorization');
    if (!token) {
      alert('请重新登录');
    } else {
      network.request({
        url: api.GetUserInfo,
        data: {},
        header: {
          'content-type': 'application/json',
          'Authorization': token // 缓存中token信息
        },
        success: function(res) {
          if (res.data.status == 200) {
            that.setData({
              userInfo: res.data.data
            })
            console.log(res.data.data);
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

  },

  changeNameAndAva: function() {
    const user = this.data.userInfo;
    // 删除之前图片
    var token = wx.getStorageSync('Authorization');
    wx.request({
      url: api.DeleteFile,
      data: {
        fileName: user.avatarUrl
      },
      header: {
        'content-type': 'application/json',
        'Authorization': token // 缓存中token信息
      },
      success: function(res) {
      }
    });
    user.avatarUrl = app.globalData.userInfo.avatarUrl;
    user.nickName = app.globalData.userInfo.nickName;
    this.updateUser(user);
  },

  changeMail: function() {
    var that = this;
    that.setData({
      mailflag: true
    })
  },

  updateOther: function() {
    var that = this;
    that.setData({
      mailflag: false
    })
    that.updateUser(that.data.userInfo);
  },

  getmail: function(e) {
    var val = e.detail;
  },
  getcode: function(e) {
    var val = e.detail;
  },
  getmsg: function(e) {
    var val = e.detail;
    const user = this.data.userInfo;
    user.sdasd = val;
    this.setData({
      userInfo: user
    })
  },

  updateUser: function(user) {
    var that = this;
    var token = wx.getStorageSync('Authorization');
    network.request({
      url: api.ResetMyInfo,
      data: user,
      method: 'POST',
      header: {
        'content-type': 'application/json',
        'Authorization': token // 缓存中token信息
      },
      success: function(res) {
        if (res.data.status == 200) {
          // 重置密匙
          wx.setStorageSync("Authorization", res.data.data);
          // 刷新页面
          that.onLoad();
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
})