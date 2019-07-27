var api = require('../../config/api.js');
Page({
  data: {
    searchKeyword: '', //需要搜索的字符
    inputShowed: true, //历史记录是否显示
    tipsShow: false, // 提示面板是否显示
    searchRecord: [], //存放历史记录
  },
  onLoad: function(options) {
    var that = this;
    that.setData({
      searchKeyword: options.key
    });
    this.openHistorySearch();
  },
  //输入框事件，每输入一个字符，就会触发一次
  bindKeywordInput: function(e) {
    var that = this;
    that.setData({
      searchKeyword: e.detail.value
    })
    if (e.detail.value == '') {
      // 显示历史记录
      that.setData({
        inputShowed: true,
        tipsShow: false
      });
    } else {
      // 显示提示
      wx.request({
        url: api.SearchTips,
        data: {
          code: 'utf-8',
          q: e.detail.value
        },
        success: function(res) {
          console.log(res.data);
          that.setData({
            tips: res.data,
            tipsShow: true,
            inputShowed: false
          });
        }
      });
    }
  },
  //搜索记录加入缓存
  addHistory: function() {
    var that = this;
    var inputVal = that.data.searchKeyword;
    var searchRecord = this.data.searchRecord;
    var z = 0;
    //加入本地缓存
    if (inputVal == '') {
      //输入为空时的处理
    } else {
      //遍历数组
      for (var i = 0; i < searchRecord.length; i++) {
        var val = searchRecord[i].value;
        if (val === inputVal) {
          if (i === 0) {
            z = 1;
            break;
          } else {
            var t = searchRecord[0].value;
            searchRecord[0].value = val;
            searchRecord[i].value = t;
            z = 2;
            break;
          }

        }
      }
      if (z === 0) {
        //将搜索值放入历史记录中,只能放前10条
        if (searchRecord.length < 10) {
          searchRecord.unshift({
            value: inputVal,
            id: searchRecord.length
          })
        } else {
          searchRecord.pop() //删掉旧的时间最早的第一条
          searchRecord.unshift({
            value: inputVal,
            id: searchRecord.length
          })
        }
      }
      //将历史记录数组整体储存到缓存中
      wx.setStorageSync('searchRecord', searchRecord);
      this.openHistorySearch();
    };
  },
  //获取缓存
  openHistorySearch: function() {
    this.setData({
      searchRecord: wx.getStorageSync('searchRecord') || [], //若无储存则为空
    })
  },
  //删除
  deleteHistory: function() {
    var that = this
    console.log(this.data.searchRecord);
    wx.showModal({
      title: '提示',
      content: '确定删除搜索历史吗',
      success: function(res) {
        if (res.confirm) {
          wx.clearStorageSync('searhRecord')
          that.setData({
            searchRecord: []
          });
          that.openHistorySearch();
        } else if (res.cancel) {}
      }
    })
  },
  //在缓存中查询关键字
  historySearch: function(event) {
    var that = this
    var keyword = event.currentTarget.dataset.historyKeyword;
    that.setData({
      searchKeyword: keyword
    });
    that.keywordSearch();
  },
  //点击搜索按钮，触发事件
  keywordSearch: function() {
    this.setData({
      inputShowed: false,
      tips: false
    })

    this.addHistory();
    // 返回上一层并携带搜索数据
    var pages = getCurrentPages();
    var prevPage = pages[pages.length - 2];
    

    prevPage.keywordSearch(this.data.searchKeyword);

    wx.navigateBack({
      delta: 1
    })
  },
  //搜索框失去或获得焦点控制缓存层的展示
  getfocus: function() {
    var that = this;
    if (that.data.searchKeyword != '') {
      that.setData({
        tipsShow: true,
      })
    } else {
      that.setData({
        inputShowed: true,
      })
    }

  },
  blurfocus: function() {
    var that = this;
    that.setData({
      inputShowed: false,
      tipsShow: false
    })
  }
})