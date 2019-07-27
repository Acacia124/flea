var ApiManagerUrl = 'https://www.flea.store';
// var ApiManagerUrl = 'http://192.168.0.104:8081';
var ApiSearchUrl = 'https://search.flea.store';
var ApiWsUrl = 'wss://www.flea.store';
module.exports = {

  // 本地持久化
  SearchHistory: 'searhRecord', //用户搜索历史
  IM: 'IMHis',  // 用户聊天列表
  IM_Prefix: 'IMHis-', //用户即时聊天历史记录前缀
  MSG: 'MSGHis',  // 站内信列表
  MSG_Prefix: 'MSGHis-',  // 站内信详情前缀
  HeadToken: 'Authorization', // 用户请求头
  AdminId: 'o-RcB5YS1uHpo7XCwboVqE3yutAE',  // 管理员Id
  MY_OPENID: 'MY_OPENID', // 本地缓存中我的openId


  //与商品管理对接
  ProductCat: ApiManagerUrl + '/api/item/cat/list', // 商品分类
  ProductDetail: ApiManagerUrl+'/api/user/item/detail', // 商品详情
  SchoolList: ApiManagerUrl +'/api/school/list',  // 学校地址列表
  SaveProduct: ApiManagerUrl + '/api/user/item/save', // 上传商品
  MyIssue: ApiManagerUrl + '/api/user/item/issue',  //我的发布
  InstockItem: ApiManagerUrl + '/api/user/item/instock', // 下架

  // 收藏对接
  CollectMy: ApiManagerUrl + '/api/user/item/collect/select',  //我的收藏
  CollectDelete: ApiManagerUrl + '/api/user/item/collect/delate',  //删除该收藏
  IsCollect: ApiManagerUrl + '/api/user/item/collect/isCollect', // 是否收藏
  UpdateCollect: ApiManagerUrl + '/api/user/item/collect/add',  // 更新收藏

  // 与检索模块对接
  SolrProduct: ApiSearchUrl + '/weChat/select', // 商品检索

  // 与内容管理模块对接
  ContentList: ApiManagerUrl + '/api/user/content/weChat/list', // 内容列表

  // 与WS即时通讯对接
  WsIM: ApiWsUrl + '/socketServer/im/',  // 在线聊天
  WsList: ApiManagerUrl + '/api/user/IM/list', // 获取未读消息用户列表

  // 与用户管理模块对接
  Login: ApiManagerUrl + '/api/login',
  CheckUser: ApiManagerUrl + '/api/user/login/check',  // 检查用户登录
  GetUserInfo: ApiManagerUrl + '/api/user/my',  // 获取我的信息
  ResetMyInfo: ApiManagerUrl + '/api/user/reset',  // 更新个人信息
  SendMail: ApiManagerUrl + '/api/login/sendMailCode', // 发送邮件接口

  // 与消息模块对接
  MsgNoRead: ApiManagerUrl + '/api/user/message/noread',  // 未读消息
  MsgSeleft: ApiManagerUrl + '/api/user/message/info',  // 查看消息内容
  MsgSend: ApiManagerUrl + '/api/user/message/all/send', // 发送消息
  MsgCount: ApiManagerUrl + '/api/user/message',  // 未读消息个数

  // 附件模块
  UploadFile: ApiManagerUrl +'/api/user/pic/upload',  // 上传图片接口
  DeleteFile: ApiManagerUrl + '/api//user/pic/delete', // 删除图片接口
  ChatUploadFile: ApiManagerUrl + '/api/user/qiniu/upload', // 聊天图片上传接口

  // 第三方api
  SearchTips: 'https://suggest.taobao.com/sug'  // 淘宝搜索提示api

};