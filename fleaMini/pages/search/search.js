var api = require('../../config/api.js');
Page({
  data: {
    searchKeyword: '', //需要搜索的字符
    searchSongList: [], //放置返回数据的数组
    isFromSearch: true, // 用于判断searchSongList数组是不是空数组，默认true，空的数组
    searchPageNum: 1, // 设置加载的第几次，默认是第一次
    callbackcount: 10, //返回数据的个数
    searchLoading: false, //"上拉加载"的变量，默认false，隐藏
    searchLoadingComplete: false, //“没有数据”的变量，默认false，隐藏

    tabTxt: ['分类', '价格', '综合排序'], //分类
    tab: [true, true, true],
    cat_id: 0, //分类
    cat_txt: '',
    sort_id: 0, //排序
  },
  onLoad: function(options) {
    var that = this;
    that.initCat();
    // 获取opentions
    var tabTxt = this.data.tabTxt;
    if (options.free != undefined) {
      tabTxt[1] = '免费',
        that.setData({
          free: options.free,
          tabTxt: tabTxt,
          searchKeyword: '*:*',
          checked: true
        })
    }
    if (options.catTxt != undefined) {
      tabTxt[0] = options.catTxt,
        that.setData({
          cat_id: options.catId,
          tabTxt: tabTxt,
          searchKeyword: '*:*',
          cat_txt: options.catTxt
        })
    }
    // 存在值则进行搜索
    this.fetchSearchList();
  },
  //搜索，访问网络
  fetchSearchList: function() {
    let that = this;
    let searchKeyword = that.data.searchKeyword, //输入框字符串作为参数
      searchPageNum = that.data.searchPageNum, //把第几次加载次数作为参数
      callbackcount = that.data.callbackcount; //返回数据的个数
    //访问网络
    wx.request({
      url: api.SolrProduct,
      data: {
        keyword: searchKeyword,
        page: searchPageNum,
        rows: callbackcount,
        price: that.data.tabTxt[1],
        sort: that.data.tabTxt[2],
        cat: that.data.tabTxt[0]
      },
      success: function(res) {
        //判断是否只有一页
        if (res.data.totalPages === 0) {
          that.setData({
            searchLoadingComplete: true, //把“没有数据”设为true，显示
            searchLoading: false //把"上拉加载"的变量设为false，隐藏
          });
        } else if (res.data.totalPages === 1) {
          let searchList = [];
          //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加
          that.data.isFromSearch ? searchList = res.data.itemList : searchList = that.data.searchSongList.concat(res.data.itemList)
          that.setData({
            searchSongList: searchList, //获取数据数组
            searchLoadingComplete: true, //把“没有数据”设为true，显示
            searchLoading: false //把"上拉加载"的变量设为false，隐藏
          });
        } else {
          //判断是否有数据，有则取数据
          if (res.data.page <= res.data.totalPages) {
            let searchList = [];
            //如果isFromSearch是true从data中取出数据，否则先从原来的数据继续添加
            that.data.isFromSearch ? searchList = res.data.itemList : searchList = that.data.searchSongList.concat(res.data.itemList)
            that.setData({
              searchSongList: searchList, //获取数据数组
              searchLoading: true //把"上拉加载"的变量设为false，显示
            });
            //没有数据了，把“没有数据”显示，把“上拉加载”隐藏
          } else {
            that.setData({
              searchLoadingComplete: true, //把“没有数据”设为true，显示
              searchLoading: false //把"上拉加载"的变量设为false，隐藏
            });
          }
        }
      }
    })
  },
  //点击搜索按钮，触发事件
  keywordSearch: function(e) {
    if (e == undefined || e == null) {
      return;
    }
    this.setData({
      searchPageNum: 1, //第一次加载，设置1
      searchSongList: [], //放置返回数据的数组,设为空
      isFromSearch: true, //第一次加载，设置true
      searchLoading: true, //把"上拉加载"的变量设为true，显示
      searchLoadingComplete: false, //把“没有数据”设为false，隐藏
      searchKeyword: e
    })
    this.fetchSearchList();
  },
  //滚动到底部触发事件
  searchScrollLower: function() {
    let that = this;
    if (that.data.searchLoading && !that.data.searchLoadingComplete) {
      that.setData({
        searchPageNum: that.data.searchPageNum + 1, //每次触发上拉事件，把searchPageNum+1
        isFromSearch: false //触发到上拉事件，把isFromSearch设为为false
      });
      that.fetchSearchList();
    }
  },
  getfocus: function() {
    wx.navigateTo({
      url: '../searchBar/searchBar?key=' + this.data.searchKeyword,
    })
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
        if (this.data.checked) {
          tabTxt[1] = '免费';
        } else {
          var lower, high;
          if (this.data.lowerPrice != undefined && this.data.lowerPrice != '') {
            lower = this.data.lowerPrice
          } else {
            lower = '*';
          }
          if (this.data.highPrice != undefined && this.data.highPrice != '') {
            high = this.data.highPrice
          } else {
            high = '*';
          }
          if (lower == '*' && high == '*') {
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
    };
    self.setData({
      searchPageNum: 1, //第一次加载，设置1
      searchSongList: [], //放置返回数据的数组,设为空
      isFromSearch: true, //第一次加载，设置true
      searchLoading: true, //把"上拉加载"的变量设为true，显示
      searchLoadingComplete: false, //把“没有数据”设为false，隐藏
    })
    self.fetchSearchList();
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