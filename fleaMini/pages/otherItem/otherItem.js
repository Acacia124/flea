var network = require('../../utils/network.js')
var api = require('../../config/api.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl:"",
    nickAme:"",
    gender: "",
    openid:"",
    create: "",
    items:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
    var that = this;
    that.setData({
      avatarUrl: options.avatarUrl,
      nickAme: options.nickAme,
      gender: options.gender,
      openid: options.openid,
      create: options.create
    })
    wx.showLoading({
      title: '加载中',
    })
    wx.request({
      url: api.MyIssue,
      data: {
        userId: that.data.openid,
        status: 0
      },
      header: {
        'content-type': 'application/json',
      },
      success: function (res) {
        wx.hideLoading();
        for (var i = 0; i < res.data.length;i++){
          res.data[i].image = res.data[i].itemImage.split(',')[0];
        }
        that.setData({
          items: res.data
        })
      },
      fail: function () {
        wx.hideLoading();
        requestHandler.fail();
      },
      complete: function () {
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})