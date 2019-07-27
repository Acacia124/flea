// index/list.js
var api = require('../../config/api.js');
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabTxt: ['分类', '价格', '综合排序'], //分类
    tab: [true, true, true],
    cat_id: 0, //分类
    cat_txt: '',
    jiage_id: 0, //价格
    jiage_txt: '',
    sort_id: 0, //排序
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {

    var that = this;
    that.initCat();
  },

  initCat: function() {
    var that = this;
    wx.request({
      url: api.ProductCat,
      data: {
        id: 0
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function(res) {
        that.setData({
          screenArray: res.data
        })
      }
    })
  },

  // 选项卡
  filterTab: function(e) {
    var data = [true, true, true],
      index = e.currentTarget.dataset.index;
    data[index] = !this.data.tab[index];
    this.setData({
      tab: data
    })
  },

  //筛选项点击操作
  filter: function(e) {
    var self = this,
      tabTxt = this.data.tabTxt;
    switch (e.currentTarget.dataset.index) {
      case '0':
        var id = e.currentTarget.dataset.id,
          txt = e.currentTarget.dataset.txt;
        tabTxt[0] = txt;
        self.setData({
          tab: [true, true, true],
          tabTxt: tabTxt,
          cat_id: id,
          cat_txt: txt
        });
        break;
      case '1':
        if(this.data.checked){
          tabTxt[1] = '免费';
        } else {
          var lower, high;
          if (this.data.lowerPrice!=undefined && this.data.lowerPrice!='') {
            lower = this.data.lowerPrice
          } else {
            lower = '不限';
          }
          if (this.data.highPrice !=undefined && this.data.highPrice != '') {
            high = this.data.highPrice
          } else {
            high = '不限';
          }
          if(lower=='不限' && high=='不限'){
            tabTxt[1] = '价格';
          } else {
            tabTxt[1] = lower + '-' + high;
          }
        }
        self.setData({
          tab: [true, true, true],
          tabTxt: tabTxt
        });
        break;
      case '2':
        var id = e.currentTarget.dataset.id,
          txt = e.currentTarget.dataset.txt;
        tabTxt[2] = txt;
        self.setData({
          tab: [true, true, true],
          tabTxt: tabTxt,
          sort_id: id,
        });
        break;
    }
  },

  onChange({
    detail
  }) {
    // 需要手动对 checked 状态进行更新
    this.setData({
      checked: detail
    });
  },

  lowerPriceFun(e) {
    this.setData({
      lowerPrice: e.detail.value
    })
    if (this.data.lowerPrice > this.data.highPrice && this.data.lowerPrice != '' && this.data.highPrice != '') {
      this.setData({
        highPrice: e.detail.value
      })
    }
  },

  highPriceFun(e) {
    this.setData({
      highPrice: e.detail.value
    })
    if (this.data.lowerPrice > this.data.highPrice && this.data.lowerPrice != '' && this.data.highPrice != '') {
      this.setData({
        lowerPrice: e.detail.value
      })
    }
  }

})