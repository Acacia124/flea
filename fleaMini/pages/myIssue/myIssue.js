var network = require('../../utils/network.js')
var api = require('../../config/api.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: "",
    nickAme: "",
    items: []
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var token = wx.getStorageSync('Authorization');
    that.setData({
      avatarUrl: options.avatarUrl,
      nickAme: options.nickAme,
    })
    wx.showLoading({
      title: '加载中',
    })
    wx.request({
      url: api.MyIssue,
      data: {
        status: 0
      },
      header: {
        'content-type': 'application/json',
        'Authorization': token // 缓存中token信息
      },
      success: function (res) {
        wx.hideLoading();
        for (var i = 0; i < res.data.length; i++) {
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
  banItem: function(e) {
    var that = this;
    var id = e.currentTarget.dataset.id;
    var title = e.currentTarget.dataset.title;
    var token = wx.getStorageSync('Authorization');
    var index = e.currentTarget.dataset.index;
    wx.showModal({
      title: '提示',
      content: '确定下架 '+ title + " 吗？",
      success: function (res) {
        if (res.confirm) {
          wx.showLoading({
            title: '下架中...',
          })
          wx.request({
            url: api.InstockItem,
            data: {
              itemId: id,
              openid: e.currentTarget.dataset.openid,
              status: 3
            },
            header: {
              'content-type': 'application/json',
              'Authorization': token // 缓存中token信息
            },
            success: function (res) {
              wx.hideLoading();
              if(res.data.status==200){
                var items = that.data.items;
                items.splice(index, 1);
                that.setData(
                  {
                    items: items
                  }
                )
                wx.showToast({
                  title: "成功删除",
                  image: '../../images/hint1.png',
                  mask: true,
                })
              } else {
                wx.showToast({
                  title: "删除商品失败",
                  image: '../../images/hint1.png',
                  mask: true,
                })
              }
            },
            fail: function () {
              wx.showToast({
                title: "删除商品失败",
                image: '../../images/hint1.png',
                mask: true,
              })
            },
          })
        } else if (res.cancel) {
          //用户取消删除
        }
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