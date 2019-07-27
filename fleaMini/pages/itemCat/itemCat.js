var network = require('../../utils/network.js')
var api = require('../../config/api.js');

Page({

  /**
   * 页面的初始数据
   */
  data: {
    currentTab: 0,  //对应样式变化
    scrollTop: 0,  //用作跳转后右侧视图回到顶部
    screenArray: [], //左侧导航栏内容
    screenId: "",  //后台查询需要的字段
    childrenArray: [], //右侧内容
    //广告条
    imageUrl: "",
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

    var that = this;
    //获得分类筛选
    this.getCatName(0);
    this.getCatName(1);
  },

  navbarTap: function (e) {
    var that = this;
    this.setData({
      currentTab: e.currentTarget.id,   //按钮CSS变化
      screenId: e.currentTarget.dataset.screenid,
      scrollTop: 0,   //切换导航后，控制右侧滚动视图回到顶部
      imageUrl: this.data.screenArray[e.currentTarget.id].imageUrl,
    })
    //刷新右侧内容的数据
    var screenId = this.data.screenId;
    this.getCatName(screenId);
  },


  //获取栏目
  getCatName: function(id) {
    var that = this;
    if(id!==0) {
      network.request({
        url: api.ProductCat,
        data: {
          id: id
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          that.setData({
            childrenArray: res.data
          })
        }
      })
    } else {
      network.request({
        url: api.ProductCat,
        data: {
          id: 0
        },
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          that.setData({
            screenArray: res.data
          })
        }
      })
    }
  },

  //事件处理函数
  switchRightTab: function (e) {
    // 获取item项的id，和数组的下标值
    let id = e.target.dataset.id,
    index = parseInt(e.target.dataset.index);
    // 把点击到的某一项，设为当前index
    this.setData({
      curNav: id,
      curIndex: index
    })
  },

  returnIssue: function(event) {
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
  
    prevPage.setData({
      catId: event.currentTarget.dataset.catId,
      catText: event.currentTarget.dataset.catText
    })

    wx.navigateBack({
      delta: 1
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