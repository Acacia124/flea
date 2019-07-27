var api = require('../../config/api.js');

Page({
  data: {
    // 通告
    notice: '',
    // top标签显示（默认不显示）
    backTopValue: false,
    // tab切换
    navbar: ['淘好货', '卖二手'],
    currentTab: 0,
    mobileModel: '', //手机型号
    // top推荐入口
    topRecomend: [{
        src: "../../images/top-borrow.png",
        text: "借还会场",
        id: '1'
      },
      {
        url: "../itemIssue/itemIssue?catId=2&catText=教材教辅",
        src: "../../images/top-book.png",
        text: "旧书速卖",
        id: '2'
      },
      {
        url: "../itemIssue/itemIssue?catId=1171&catText=手机充值",
        src: "../../images/top-card.png",
        text: "卡劵速卖",
        id: '3'
      },
      {
        url: "../itemIssue/itemIssue?catId=127&catText=数码相机",
        src: "../../images/top-digital.png",
        text: "数码速卖",
        id: '4'
      }
    ],
    //分类块
    arry1: [{
        src: "../../images/top-free.png",
        text: "免费专场",
        url: '../search/search?free=true',
        id: '6'
      },
      {
        src: "../../images/book.png",
        text: "图书音像",
        url: '../search/search?catTxt=图书&catId=1',
        id: '2'
      },
      {
        src: "../../images/clothing.png",
        text: "服饰箱包",
        url: '../search/search?catTxt=服饰箱包&catId=749',
        id: '3'
      },
      {
        src: "../../images/computer.png",
        text: "电脑及配件",
        url: '../search/search?catTxt=电脑&catId=27',
        id: '4'
      },
      {
        src: "../../images/phone.png",
        text: "手机及配件",
        url: '../search/search?catTxt=手机&catId=98',
        id: '5'
      },
      {
        src: "../../images/digital.png",
        text: "数码影音",
        url: '../search/search?catTxt=数码&catId=126',
        id: '6'
      },
      {
        src: "../../images/exercise.png",
        text: "户外运动",
        url: '../search/search?catTxt=运动健康&catId=1031',
        id: '7'
      },
      {
        src: "../../images/furniture.png",
        text: "宿舍家纺",
        url: '../search/search?catTxt=宿舍家纺&catId=634',
        id: '8'
      },
    ],
    //热卖
    "items": [{
        "id": "1",
        "imageUrl": "../../images/hot-digital.png",
        "content": "二手数码专场",
        "url": "../itemIssue/itemIssue?catId=127&catText=数码相机",
      },
      {
        "id": "2",
        "imageUrl": "../../images/hot-home.jpg",
        "content": "宿舍用品专场",
        "url": "../itemIssue/itemIssue?catId=667&catText=家装软饰",
      },
      {
        "id": "3",
        "imageUrl": "../../images/hot-book.png",
        "content": "教材小说专场",
        "url": "../itemIssue/itemIssue?catId=3&catText=文学小说",
      },
      {
        "id": "4",
        "imageUrl": "../../images/hot-computer.png",
        "content": "手机电脑及配件专场",
        "url": "../itemIssue/itemIssue?catId=31&catText=平板电脑",
      },
      {
        "id": "5",
        "imageUrl": "../../images/hot-clothing.png",
        "content": "服饰箱包专场",
        "url": "../itemIssue/itemIssue?catId=762&catText=衣帽配饰",
      },
      {
        "id": "6",
        "imageUrl": "../../images/hot-jita.png",
        "content": "玩具乐器专场",
        "url": "../itemIssue/itemIssue?catId=90&catText=吉他/贝斯",
      }
    ],
    //轮播图
    imgUrls: [],
    indicatorDots: true, //是否显示面板指示点
    autoplay: true, //是否自动切换
    interval: 3000, //自动切换时间间隔
    duration: 1000, //滑动动画时长
    inputShowed: false,
    inputVal: "",

    //流式布局
    scrollH: 0,
    imgWidth: 0,
    loadingCount: 0,
    images: [],
    col1: [],
    searchPageNum: 1, //当前页码
    callbackcount: 5, //每页显示条数
    searchLoadingComplete: true, //“没有数据”的变量，默认true
    col2: [],
    //地图
    // myArea: []
  },

  navigateToPage: function(e) {
    var imageUrl = e.currentTarget.id;
    wx.navigateTo({
      url: imageUrl,
    })
  },

  //tab改变事件 
  navbarTap: function(e) {
    this.setData({
      currentTab: e.currentTarget.dataset.idx
    })
    if (e.currentTarget.dataset.idx == 1) {
      this.backTop();
    }
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    var that = this;
    that.getSystem();
    // this.loadInfo();
    //商品列表
    that.initImages();

    that.loadCarouselMap();
  },

  //商品列表
  //初始化
  initImages: function() {
    wx.getSystemInfo({
      success: (res) => {
        let ww = res.windowWidth;
        let wh = res.windowHeight;
        let imgWidth = ww * 0.48;
        let scrollH = wh;

        this.setData({
          scrollH: scrollH,
          imgWidth: imgWidth
        });

        //加载首组图片
        this.loadImages();
      }
    })
  },

  //下拉刷新
  onPullDownRefresh: function() {
    var that = this;
    if (that.data.currentTab == 0) {
      wx.showNavigationBarLoading();
      // complete
      that.setData({
        scrollH: 0,
        imgWidth: 0,
        loadingCount: 0,
        images: [],
        col1: [],
        searchPageNum: 1, //当前页码
        callbackcount: 5, //每页显示条数
        searchLoadingComplete: true, //“没有数据”的变量，默认true
        col2: [],
      })
      that.onLoad();
      setTimeout(function(){
        wx.hideNavigationBarLoading() //完成停止加载
        wx.stopPullDownRefresh() //停止下拉刷新
      },2000)
    } else {
      wx.stopPullDownRefresh()
    }
  },

  onImageLoad: function(e) {
    let imageId = e.currentTarget.id;
    let oImgW = e.detail.width; //图片原始宽度
    let oImgH = e.detail.height; //图片原始高度
    let imgWidth = this.data.imgWidth; //图片设置的宽度
    let scale = imgWidth / oImgW; //比例计算
    let imgHeight = oImgH * scale; //自适应高度
    let images = this.data.images;
    let imageObj = [];

    for (let i = 0; i < images.length; i++) {
      let img = images[i];
      if (img.fid === imageId) {
        imageObj = img;
        break;
      }
    }

    imageObj.height = imgHeight;

    let loadingCount = this.data.loadingCount - 1;
    let col1 = this.data.col1;
    let col2 = this.data.col2;

    //判断当前图片添加到左列还是右列
    if (col1.length <= col2.length) {
      col1.push(imageObj);
    } else {
      col2.push(imageObj);
    }

    let data = {
      loadingCount: loadingCount,
      col1: col1,
      col2: col2
    };

    //当前这组图片已加载完毕，则清空图片临时加载区域的内容
    if (!loadingCount) {
      data.images = [];
    }

    this.setData(data);
  },

  loadImages: function() {
    var that = this;
    if (that.data.searchLoadingComplete) {
      wx.request({
        url: api.SolrProduct,
        data: {
          keyword: "*:*",
          page: that.data.searchPageNum,
          rows: that.data.callbackcount,
          free: 0
        },
        success: function(res) {
          let images = res.data.itemList;

          let baseId = "img-" + (+new Date());

          for (let i = 0; i < images.length; i++) {
            images[i].fid = baseId + "-" + i;
            images[i].height = 0;
          }
          that.setData({
            loadingCount: images.length,
            images: images,
            searchPageNum: that.data.searchPageNum + 1
          });
          if (res.data.totalPages < that.data.searchPageNum) {
            that.setData({
              searchLoadingComplete: false
            })
          }
        }
      });
    }
  },

  //获取手机型号
  getSystem: function() {
    var that = this;
    wx.getSystemInfo({
      success: function(res) {
        that.setData({
          mobileModel: res.model
        })
      }
    })
  },

  // 加载内容管理模块
  loadCarouselMap: function() {
    var that = this;
    wx.request({
      url: api.ContentList,
      data: {
        categoryId: 35
      },
      success: function (res) {
        if (res.data!=null) {
          that.setData({
            imgUrls: res.data
          })
        }
      }
    });
    wx.request({
      url: api.ContentList,
      data: {
        categoryId: 32
      },
      success: function (res) {
        if (res.data != null && res.data.length>0) {
          var data = res.data[0].contentTitle + ": " + res.data[0].titleDesc
          that.setData({
            notice: data
          })
        }
      }
    })
  },


  onReachBottom: function(e) {
    var that = this;
    if (that.data.currentTab == 0) {
      that.loadImages();
    }
  },

  // 监听滚动条坐标
  onPageScroll: function(e) {
    var that = this
    var scrollTop = e.scrollTop
    var backTopValue = scrollTop > 330 ? true : false
    that.setData({
      backTopValue: backTopValue
    })
  },

  // 滚动到顶部
  backTop: function() {
    // 控制滚动
    wx.pageScrollTo({
      scrollTop: 0
    })
  },
})