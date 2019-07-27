// pages/mine/mine.js
var network = require('../../utils/network.js')
var api = require('../../config/api.js');

Page({
  data: {
    //判断小程序的API，回调，参数，组件等是否在当前版本可用。
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    userInfo: {},
    hasUserInfo: false,
    code: '',
    rawData: []
  },
  onLoad: function() {
    var that = this;
    // 查看是否授权
    wx.getSetting({
      success: function(res) {
        if (res.authSetting['scope.userInfo']) {
          wx.login({
            success: function(loginRes) {
              if (loginRes) {
                //获取用户信息
                wx.getUserInfo({
                  withCredentials: true, //非必填  默认为true
                  success: function(infoRes) {
                    that.setData({
                      userInfo: infoRes.userInfo,
                      hasUserInfo: true,
                      code: loginRes.code,
                      rawData: infoRes.rawData,
                    });
                    var token = wx.getStorageSync('Authorization');
                    if (!token) {
                      //请求服务端的登录接口
                      that.mylogin();
                    } else {
                      wx.request({
                        url: api.CheckUser,
                        data: '',
                        header: {
                          'content-type': 'application/json',
                          'Authorization': token // 缓存中token信息
                        },
                        success: function(res) {
                          if (res.data.status == 200) {
                            that.getMsgNoread();
                          } else if (res.data.status == 402) {
                            wx.showToast({
                              title: res.data.msg,
                              image: '../../images/hint2.png',
                              mask: true,
                            })
                          } else {
                            //请求服务端的登录接口
                            that.mylogin();
                          }
                        }
                      })
                    }
                  }
                });
              } else {

              }
            }
          });
        }
      }
    })
  },

  //未授权过
  getUserInfo: function(e) {
    var that = this;
    if (e.detail.userInfo) {
      that.onLoad();
    } else {
      //用户按了拒绝按钮
    }
  },

  //登录
  mylogin: function() {
    var that = this;
    //请求服务端的登录接口
    network.request({
      url: api.Login,
      data: {
        code: that.data.code, //临时登录凭证
        rawData: that.data.rawData, //用户非敏感信息
      },
      success: function(res) {
        var cookie = res.data;
        //状态200返回token并且存储
        if (cookie.status == 200) {
          wx.setStorageSync("Authorization", cookie.data)
          wx.showToast({
            title: '登陆成功',
            icon: 'success',
            mask: true,
          })
          that.getMsgNoread();
        } else {
          that.setData({
            hasUserInfo: false
          });
        }
      },
      //调用服务端登录接口失败
      fail: function(error) {
        wx.showToast({
          title: '检查你的网络',
          image: '../../images/hint1.png',
          mask: true,
        })
      }
    });
  },

  getMsgNoread: function() {
    var that = this;
    var token = wx.getStorageSync('Authorization');
    wx.request({
      url: api.MsgCount,
      header: {
        'content-type': 'application/json',
        'Authorization': token // 缓存中token信息
      },
      success: function(res) {
        that.setData({
          count: res.data.data
        })
      }
    })
  }
})