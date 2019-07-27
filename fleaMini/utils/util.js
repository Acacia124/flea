const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

const formatNumber = n => {
  n = n.toString()
  return n[1] ? n : '0' + n
}

/**
 * 格式化msgList日期
 */
function fomartMsgList(msgList) {
  let msgListCopy = msgList;
  for (let i = msgList.length-1;i>0;i--){
    let createTimeFirst =  new Date(msgList[i].createTime).getTime();
    let createTimeSecond = new Date(msgList[i - 1].createTime).getTime();
    let minute = (createTimeFirst-createTimeSecond)/1000/60;
    if(minute>5) {
      msgListCopy[i].format = true;
      msgListCopy[i].time = toWeiXinString(new Date(msgList[i].createTime));
    }
  }
  if(msgList.length>0) {
    msgListCopy[0].format = true;
    msgListCopy[0].time = toWeiXinString(new Date(msgList[0].createTime));
  } 
  return msgListCopy;
}

function toWeiXinString(date) {
  let str;
  const now = new Date();
  const today = new Date(now.getFullYear(), now.getMonth(), now.getDate());
  const yesterday = new Date(now.getFullYear(), now.getMonth(), now.getDate() - 1);
  const beforeYesterday = new Date(now.getFullYear(), now.getMonth(), now.getDate() - 2);
  const monday = new Date(today);
  monday.setDate(today.getDate() - (today.getDay() ? today.getDay() - 1 : 6));
  //注意：date初始化默认是按本地时间初始的，但打印默认却是按GMT时间打印的，也就是说打印出的不是本地现在的时间
  //LocaleString的打印也有点问题，"0点"会被打印为"上午12点"
  if (date.getTime() > today.getTime()) {
    str = "";
  } else if (date.getTime() > yesterday.getTime()) {
    str = "昨天";
  } else if (date.getTime() > beforeYesterday.getTime()) {
    str = "前天";
  } else if (date.getTime() > monday.getTime()) {
    const week = { "0": "周日", "1": "周一", "2": "周二", "3": "周三", "4": "周四", "5": "周五", "6": "周六" };
    str = week[date.getDay() + ""];
  } else {
    const hour = ["凌晨", "早上", "下午", "晚上"];
    const h = date.getHours();
    if (h == 12) str = "中午";
    else str = hour[parseInt(h / 6)];
    str = date.format("MM月dd ") + str;
  }
  str += date.format("HH:mm");
  return str;
}

Date.prototype.format = function (fmt) {
  const o = {
    "y+": this.getFullYear(),
    "M+": this.getMonth() + 1,
    "d+": this.getDate(),
    "H+": this.getHours(),
    "m+": this.getMinutes(),
    "s+": this.getSeconds(),
    "S+": this.getMilliseconds(),
    "q+": Math.floor(this.getMonth() / 3) + 1,
    "h+": (() => {
      const hour = this.getHours() % 12;
      return hour == 0 ? 12 : hour;
    })(),
    "E+": (() => {
      const week = { "0": "Sunday", "1": "Monday", "2": "Tuesday", "3": "Wednesday", "4": "Thursday", "5": "Friday", "6": "Saturday" };
      return week[this.getDay() + ""];
    })(),
		/*
		"e+": (()=>{
			const week = {"0":"Sun","1":"Mon","2":"Tue","3":"Wed","4":"Thu","5":"Fri","6":"Sat"}; 
			return week[this.getDay()+""];
		})(),
		*/
    "x1": (() => {
      const week = { "0": "周日", "1": "周一", "2": "周二", "3": "周三", "4": "周四", "5": "周五", "6": "周六" };
      return week[this.getDay() + ""];
    })(),
    "x2": (() => {
      const hour = ["凌晨", "早上", "下午", "晚上"];
      const h = this.getHours();
      if (h == 12) return "中午";
      return hour[parseInt(h / 6)];
    })(),
  }
  for (var k in o) {
    if (new RegExp("(" + k + ")", "g").test(fmt)) {
      const len = RegExp.$1.length;
      fmt = fmt.replace(RegExp.$1, len == 1 ? o[k] : ("00" + o[k]).substr(-len));
    }
  }
  return fmt;
}


//上传方法
function upload_file_server(url, that, upload_picture_list, j,token) {
  //上传返回值
  const upload_task = wx.uploadFile({
    // 模拟https
    url: url, //需要用HTTPS，同时在微信公众平台后台添加服务器地址  
    filePath: upload_picture_list[j]['path'], //上传的文件本地地址    
    name: 'uploadFile',
    header: {
      'Authorization': token // 缓存中token信息
    },
    formData: {
      'num': j
    },

    //附近数据，这里为路径     
    success: function (res) {

      var data = JSON.parse(res.data);
      // //字符串转化为JSON  
      if (data.status == 200) {

        var filename = data.data //存储地址 显示

        upload_picture_list[j]['path_server'] = filename
      } else {
        upload_picture_list[j]['path_server'] = filename;
        wx.showToast({
          title: '请登录后上传',
          image: '../../images/hint1.png',
          mask: true,
        })
      }
      that.setData({
        upload_picture_list: upload_picture_list
      });

      wx.setStorageSync('imgs', upload_picture_list);
    }
  })
  //上传 进度方法
  upload_task.onProgressUpdate((res) => {
    upload_picture_list[j]['upload_percent'] = res.progress;
    that.setData({
      upload_picture_list: upload_picture_list
    });
  });
}

module.exports = {
  formatTime: formatTime,
  upload_file_server: upload_file_server,
  toWeiXinString: toWeiXinString,
  fomartMsgList, fomartMsgList
}