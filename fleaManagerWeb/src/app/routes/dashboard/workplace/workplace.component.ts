import { Component, OnInit, AfterViewChecked, OnDestroy, Inject, ViewChild, ElementRef } from '@angular/core';
import { zip, Subscription } from 'rxjs';
import { NzMessageService } from 'ng-zorro-antd';
import { getTimeDistance, yuan } from '@delon/util';
import { _HttpClient } from '@delon/theme';
import { RxStompService, StompService } from '@stomp/ng2-stompjs';
import { Message } from '@stomp/stompjs';
import { TokenService, DA_SERVICE_TOKEN } from '@delon/auth';
import { HttpHeaders } from '@angular/common/http';
import { NgxEchartsDirective } from 'ngx-echarts/lib/ngx-echarts.directive';
import { NgxEchartsService } from 'ngx-echarts';
declare var navigator: Navigator;
declare var document: Document;



@Component({
  selector: 'app-dashboard-workplace',
  templateUrl: './workplace.component.html',
  styleUrls: ['./workplace.component.less'],
})
export class DashboardWorkplaceComponent implements OnInit, OnDestroy, AfterViewChecked {

  expandKeys = ['100', '1001'];
  topicSubscription: Subscription;
  notice: any[] = [];
  loading = true;
  count = 0;
  interval$: any;
  interval2$: any;
  current = 0;
  img = '';

  tabs = [];
  selectedIndex = 0;

  salesData: any[] = new Array(12).fill({}).map((i, idx) => ({
    x: `${idx + 1}月`,
    y: Math.floor(Math.random() * 1000) + 200,
  }));


  // 实时日志
  logName = '';
  public receivedMessages = [];
  isVisible = false;
  @ViewChild('scrollMe') private myScrollContainer: ElementRef;

  canvas = '';

  // redis
  redisInfoList: null;
  redisLogLen: 0;
  redisLogList: null;
  redisMemory: any[] = [];
  data = [];
  option: any;
  option2: any;
  @ViewChild('memory') private myChat: ElementRef;
  // @ViewChild('rediskeys') private myChat2: ElementRef;

  constructor(private el: ElementRef, private es: NgxEchartsService, private http: _HttpClient, public msg: NzMessageService,
    @Inject(DA_SERVICE_TOKEN) private tokenService: TokenService,
    private rxStompService: RxStompService) { }

  ngOnInit() {
    // 加载用户信息
    this.img = this.tokenService.get().avatarUrl;
    // 初始化服务器
    this.notice = [{
      description: '包含JWT单点登录、商城管理、用户管理等模块功能具体实现',
      href: '',
      id: '0',
      logo: 'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
      member: '科学搬砖组',
      memberLink: '',
      title: '商城管理Service服务器',
      ip: '101.200.56.187'
    },
    {
      description: '包含了与商城管理Service层相对应的Controller层服务',
      href: '',
      id: '1',
      logo: 'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
      member: '科学搬砖组',
      memberLink: '',
      title: '商城管理Controller服务器',
      ip: '101.200.56.187'
    },
    {
      description: '对商品发布以及删除时对Solr的具体操作',
      href: '',
      id: '2',
      logo: 'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
      member: '科学搬砖组',
      memberLink: '',
      title: '商品检索Service服务器',
      ip: '47.106.181.166'
    },
    {
      description: '是对商品检索Service层相对应的Controller层服务',
      href: '',
      id: '3',
      logo: 'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
      member: '科学搬砖组',
      memberLink: '',
      title: '商品检索Controller服务器',
      ip: '47.106.181.166'
    },
    {
      description: '对商城内容的管理，如轮播图广告的展示',
      href: '',
      id: '4',
      logo: 'https://gw.alipayobjects.com/zos/rmsportal/WdGqmHpayyMjiEhcKoVE.png',
      member: '科学搬砖组',
      memberLink: '',
      title: '内容管理Service服务器',
      ip: '47.106.181.166'
    }];

    // 初始化日志列表
    this.tabs = [
      {
        name: '商城管理Service',
        content: []
      },
      {
        name: '商城管理Controller',
        content: []
      },
      {
        name: '商品检索Service',
        content: []
      },
      {
        name: '商品检索Controller',
        content: []
      },
      {
        name: '内容管理Service',
        content: []
      }
    ];

    // 初始化日志列表
    this.logList(0);

    this.loading = false;

    this.initRedisMemory(5);

    this.getRedisInfo();
  }

  /**
   * 实时日志查看
   */
  realTimeLog(index: any) {
    let url: any;
    let topic: any;
    if (index === 0) {
      url = 'ws://101.200.56.187:8080/websocket//websocket';
      topic = '/topic/pullLogger1';
      this.logName = '商城管理Service层实时日志';
    } else if (index === 1) {
      url = 'ws://101.200.56.187:8081/websocket//websocket';
      topic = '/topic/pullLogger2';
      this.logName = '商城管理Controller层实时日志';
    } else if (index === 2) {
      url = 'ws://47.106.181.166:8080/search/websocket//websocket';
      topic = '/topic/pullLoggerSearch1';
      this.logName = '商城检索Service层实时日志';
    } else if (index === 3) {
      url = 'ws://47.106.181.166:8081/websocket//websocket';
      topic = '/topic/pullLoggerSearch2';
      this.logName = '商城检索Controller层实时日志';
    } else if (index === 4) {
      url = 'ws://47.106.181.166:8080/content/websocket//websocket';
      topic = '/topic/pullLoggerContent';
      this.logName = '内容管理Service层实时日志';
    }

    // 初始化webSocket连接配置
    this.rxStompService.configure({
      brokerURL: url,
      connectHeaders: {
        // JWT校验头
        Authorization: this.tokenService.get()
      },
      heartbeatIncoming: 0,
      heartbeatOutgoing: 20000,
      reconnectDelay: 200,
      // tslint:disable-next-line:no-shadowed-variable
      debug: (msg: string): void => {
        if (this.receivedMessages.length === 0) {
          this.receivedMessages.push({
            level: 'PROMPT',
            body: '正在与WebSocket建立连接... ...'
          });
        }
        if (msg.indexOf('SUBSCRIBE') !== -1) {
          this.receivedMessages.push({
            level: 'PROMPT',
            body: 'WebSocket连接成功.'
          });
        }
        if (msg.indexOf('Connection closed to') !== -1) {
          this.receivedMessages.push({
            level: 'PROMPT',
            body: 'WebSocket连接意外中断.'
          });
        }
      }
    });
    this.rxStompService.activate();
    // 订阅日志消息
    this.topicSubscription = this.rxStompService.watch(topic).subscribe((message: Message) => {
      this.receivedMessages.push(JSON.parse(message.body));
    });


    this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    this.isVisible = true;
  }

  /**
   * 控制实时日志div显示在最底层
   */
  ngAfterViewChecked() {
    this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
  }


  /**
   * 日志查看取消
   */
  closeSocket() {
    this.receivedMessages = [];
    this.topicSubscription.unsubscribe();
    this.rxStompService.deactivate();
    this.isVisible = false;
  }

  /**
   * 导航到某一日志
   */
  toLog(index: any) {
    this.selectedIndex = index;
    this.el.nativeElement.querySelector('#logList').style.height = '300px';
  }


  /**
   * 根据index获取查看日志列表信息
   */
  logList(index) {
    let url;
    if (index === 0) {
      url = '/user/log/manager/list/1';
    } else if (index === 1) {
      url = '/user/log/manager/list/2';
    } else if (index === 2) {
      url = 'https://search.flea.store/api/user/log/search/list/1';
    } else if (index === 3) {
      url = 'https://search.flea.store/api/user/log/search/list/2';
    } else if (index === 4) {
      url = '/user/log/content/list/1';
    } else {
      return;
    }
    this.http.get<any>(url).subscribe(
      res => {
        this.tabs[index].content = res.data;
      }
    );
  }

  /**
   * 获取Redis信息
   */
  getRedisInfo() {
    this.http.get<any>('/user/redisMonitor').subscribe(
      res => {
        if (res.status === 200) {
          const data = res.data;
          this.redisInfoList = data.infoList;
          this.redisLogLen = data.logLen;
          this.redisLogList = data.logList;
        }
      }
    );
  }

  /**
   * 获取Redis当前时间点后n（s）个内存状态信息
   */
  initRedisMemory(number) {
    this.http.get<any>('/user/getMemory/' + number).subscribe(
      res => {
        if (res.status === 200) {
          for (let i = 0; i < number; i++) {
            const t = {
              value: [
                [
                  (new Date(res.data[i].name)).getFullYear(),
                  (new Date(res.data[i].name)).getMonth(),
                  (new Date(res.data[i].name)).getDate()
                ].join('/') + ' ' + [
                  (new Date(res.data[i].name)).getHours(),
                  (new Date(res.data[i].name)).getMinutes(),
                  (new Date(res.data[i].name)).getSeconds()
                ].join(':'),
                (res.data[i].value / 1024 / 1024).toFixed(2)
              ]
            };
            this.data.push(t);
          }
          this.option = {
            grid: {
              x: '8%',
              y: '5%',
              x2: '2.4%',
              y2: '12%',
            },
            xAxis: {
              type: 'time',
              splitNumber: 10
            },
            yAxis: {
              type: 'value',
              axisLabel: {
                formatter: '{value} M'
              },
            },
            dataZoom: [
              {
                type: 'slider',
                start: 0,
                minSpan: 8,
                dataBackground: {
                  lineStyle: {
                    color: '#95BC2F'
                  },
                  areaStyle: {
                    color: '#95BC2F',
                    opacity: 1,
                  }
                },
              },
              {
                type: 'inside'
              }
            ],
            tooltip: {
              trigger: 'axis',
              formatter: function (params) {
                let result = params[0].value[0];
                params.forEach(function (item) {
                  result += '<br/>';
                  result += '<span style="display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:'
                    + item.color + '"></span>';
                  result += item.seriesName + '：';
                  result += isNaN(item.value[1]) ? 0 : item.value[1] + 'M';
                });
                return result;
              },
            },
            series: {
              name: '占用内存',
              type: 'line',
              showSymbol: false,
              symbolSize: 12,
              data: this.data,
            }
          };
        }
      }
    );
    this.getRedisMemory();
  }

  /**
   * Redis 实时内存状态(5s一次)
   */
  getRedisMemory() {
    let count = 0;
    this.interval$ = setInterval(() => {
      count++;
      this.http.get<any>('/user/getMemeryInfo?count=' + count).subscribe(
        res => {
          if (res.status === 200) {

            const t = {
              value: [
                [
                  (new Date(res.data.name)).getFullYear(),
                  (new Date(res.data.name)).getMonth(),
                  (new Date(res.data.name)).getDate()
                ].join('/') + ' ' + [
                  (new Date(res.data.name)).getHours(),
                  (new Date(res.data.name)).getMinutes(),
                  (new Date(res.data.name)).getSeconds()
                ].join(':'),
                (res.data.value / 1024 / 1024).toFixed(2)
              ]
            };
            if (this.data.length > 100)
              this.data.shift();
            this.data.push(t);
            const mychat = this.es.getInstanceByDom(this.myChat.nativeElement);
            if (mychat !== undefined) {
              mychat.setOption({
                series: [{
                  data: this.data
                }]
              });
            }
          }
        }
      );
    }, 5000);
  }

  /**
   * 删除日志RedisSlowLog
   */
  cleanRedisLog() {
    this.http.get<any>('/user/logEmpty')
      .subscribe(
        res => {
          if (res.status === 200) {
            this.redisLogLen = 0;
            this.redisLogList = null;
          } else {
            this.msg.error('Redis日志删除失败：' + res.data);
          }
        }
      );
  }

  /**
   * 下载日志
   */
  download(log) {
    let url;
    if (this.selectedIndex === 0) {
      url = '/user/log/manager/download/1';
    } else if (this.selectedIndex === 1) {
      url = '/user/log/manager/download/2';
    } else if (this.selectedIndex === 2) {
      url = 'https://search.flea.store/api/user/log/search/download/1';
    } else {
      this.msg.info('该服务器暂时为实现该功能');
      return;
    }
    this.http.get(url, { 'fileName': log }, { responseType: 'blob', observe: 'response' }).subscribe(
      data => {
        const link = document.createElement('a');
        const blob = new Blob([data.body], { type: 'application/octet-stream' });
        link.setAttribute('href', window.URL.createObjectURL(blob));
        link.setAttribute('download', log);
        link.style.visibility = 'hidden';
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
      }
    );
  }

  /**
   * 开启摄像头
   */
  clickVideo() {
    const constraints = {
      video: { width: 158, height: 158 },
      audio: true
    };
    const promise = navigator.mediaDevices.getUserMedia(constraints);
    promise.then(function (MediaStream) {
      let video: any;
      video = document.getElementById('video');
      video.srcObject = MediaStream;
      video.play();
    });
  }

  /**
   * 拍照
   */
  takePhoto() {
    // 获得Canvas对象
    const video = document.getElementById('video');
    let canvas: any;
    canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');
    ctx.drawImage(video, 0, 0, video.offsetWidth, video.offsetHeight);
    this.canvas = canvas.toDataURL('image/jpeg', 1.0);
  }


  /**
   * 更改密匙
   */
  pre(): void {
    this.current -= 1;
  }

  next(): void {
    if (this.current === 0) {
      if (this.canvas === '') {
        this.msg.error('请拍照进入下一环节');
      } else {
        this.http.post<any>('/face/verify', { 'base64Img': this.canvas })
          .subscribe(
            res => {
              if (res.status === 200) {
                if ('SUCCESS' === res.data.error_msg) {
                  if (res.data.result.user_list[0].score > 70) {
                    this.current += 1;
                  } else {
                    this.msg.error(`人脸识别率（${res.data.result.user_list[0].score}）未通过`);
                  }
                } else {
                  this.msg.error(res.data.error_msg);
                }
              } else {
                this.msg.error(res.msg);
              }
            }
          );
      }
    } else if (this.current === 1) {
      this.current += 1;
    } else if (this.current === 2) {
      this.current = 0;
    }
  }

  done(): void {
    this.msg.info('更改密匙');
  }

  /**
   * 导入所有商品到Solr
   */
  importSolr() {
    this.http.get('/user/solr/import').subscribe(
      res => {
        console.log(res);
      }
    );
  }

  /**
   * 清空Solr库
   */
  deleteSolr() {
    this.http.get('/user/solr/delete').subscribe(
      res => {
        console.log(res);
      }
    );
  }
  // getCaptcha() {
  //   this.count = 59;
  //   this.count -= 1;
  //   this.interval$ = setInterval(() => {
  //     this.count -= 1;
  //     if (this.count <= 0) clearInterval(this.interval$);
  //   }, 1000);
  // }


  ngOnDestroy(): void {
    if (this.interval$) clearInterval(this.interval$);
    if (this.interval2$) clearInterval(this.interval2$);
  }
}
