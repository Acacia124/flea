// pages/mylove/mylove.js
var api = require('../../config/api.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    navbar: ['在卖宝贝', '失效宝贝'],
    currentTab: 0,
    items: [],
  },

  /**
   * 页面切换
   */
  navbarTap: function (e) {
    this.setData({
      currentTab: e.currentTarget.dataset.idx
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var token = wx.getStorageSync('Authorization');
    wx.showLoading({
      title: '加载中',
    })
    wx.request({
      url: api.CollectMy,
      data: {},
      header: {
        'content-type': 'application/json',
        'Authorization': token // 缓存中token信息
      },
      success: function (res) {
        wx.hideLoading();
        if (res.data.status == 200) {
          for (var i = 0; i < res.data.data.length; i++) {
            that.data.items.push({
              item: res.data.data[i],
              isTouchMove: false //默认隐藏删除
            })
            console.log(that.data.items);
          }
          that.setData({
            items: that.data.items
          })
        } else {

        }
      },
      fail: function () {
        wx.hideLoading();
        requestHandler.fail();
      },
      complete: function () {
      }
    })

  },

  //手指触摸动作开始 记录起点X坐标

  touchstart: function (e) {

    //开始触摸时 重置所有删除

    this.data.items.forEach(function (v, i) {

      if (v.isTouchMove)//只操作为true的

        v.isTouchMove = false;

    })

    this.setData({

      startX: e.changedTouches[0].clientX,

      startY: e.changedTouches[0].clientY,

      items: this.data.items

    })

  },

  //滑动事件处理

  touchmove: function (e) {

    var that = this,

      index = e.currentTarget.dataset.index,//当前索引

      startX = that.data.startX,//开始X坐标

      startY = that.data.startY,//开始Y坐标

      touchMoveX = e.changedTouches[0].clientX,//滑动变化坐标

      touchMoveY = e.changedTouches[0].clientY,//滑动变化坐标

      //获取滑动角度

      angle = that.angle({ X: startX, Y: startY }, { X: touchMoveX, Y: touchMoveY });

    that.data.items.forEach(function (v, i) {

      v.isTouchMove = false

      //滑动超过30度角 return

      if (Math.abs(angle) > 30) return;

      if (i == index) {

        if (touchMoveX > startX) //右滑

          v.isTouchMove = false

        else //左滑

          v.isTouchMove = true

      }

    })

    //更新数据

    that.setData({

      items: that.data.items

    })

  },

  /**
  
  * 计算滑动角度
  
  * @param {Object} start 起点坐标
  
  * @param {Object} end 终点坐标
  
  */

  angle: function (start, end) {

    var _X = end.X - start.X,

      _Y = end.Y - start.Y

    //返回角度 /Math.atan()返回数字的反正切值

    return 360 * Math.atan(_Y / _X) / (2 * Math.PI);

  },

  //删除事件

  del: function (e) {

    var that = this;
    var token = wx.getStorageSync('Authorization');

    this.data.items.splice(e.currentTarget.dataset.index, 1)

    var itemId = e.currentTarget.dataset.id
    

    wx.showLoading({
      title: '删除中',
    })
    wx.request({
      url: api.CollectDelete,
      data: {
        itemId:itemId
      },
      header: {
        'content-type': 'application/json',
        'Authorization': token // 缓存中token信息
      },
      success: function (res) {
        wx.hideLoading();
        if (res.data.status == 200) {
          that.setData({
            items: that.data.items
          })
        } else {
        }
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