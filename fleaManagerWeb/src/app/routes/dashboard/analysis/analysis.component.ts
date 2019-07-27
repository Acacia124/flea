import {
  Component,
  ChangeDetectionStrategy,
  OnInit,
  ChangeDetectorRef,
  ViewChild,
  ElementRef,
} from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { STColumn } from '@delon/abc';
import { getTimeDistance } from '@delon/util';
import { _HttpClient } from '@delon/theme';
import { NgxEchartsService } from 'ngx-echarts';
@Component({
  selector: 'app-dashboard-analysis',
  templateUrl: './analysis.component.html',
  styleUrls: ['./analysis.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DashboardAnalysisComponent implements OnInit {
  data: any = {};
  loading = true;
  date_range: Date[] = [];
  hisTop: any = [];
  hisG2: any = [];
  titleMap = {
    y1: '发布量',
    y2: '下架量'
  };
  itemCount: number;
  itemWeek: number;
  itemLastWeek: number;
  itemToday: number;
  itemYesterday: number;

  userCount: number;
  userWeek: number;
  userLastWeek: number;
  userToday: number;
  userYesterday: number;

  option: any;
  @ViewChild('memory') private myChat: ElementRef;


  constructor(
    private http: _HttpClient,
    public msg: NzMessageService,
    private cdr: ChangeDetectorRef,
    private es: NgxEchartsService,
  ) { }

  ngOnInit() {
    this.date_range = getTimeDistance('month');
    this.initData(this.date_range);
  }

  changeVisitG2(date) {
    let status;
    if ((date[1] - date[0]) < (60 * 60 * 24 * 2 * 1000)) {
      status = 1;
    } else if ((date[1] - date[0]) < (60 * 60 * 24 * 30 * 6 * 1000)) {
      status = 2;
    } else {
      status = 4;
    }

    this.http.get('/user/his/g2', { start: date[0], end: date[1], status: status })
      .subscribe(
        res => {
          this.hisG2 = res;
        }
      );
    this.http.get('/user/his/top', { start: date[0], end: date[1] })
      .subscribe(
        res => {
          this.hisTop = res;
        }
      );
  }

  initData(date) {
    this.http.get<any>('/user/analysis', { start: date[0], end: date[1] })
      .subscribe(
        res => {
          if (res.status === 500) {
            this.msg.error('数据初始化出错！');
            this.loading = false;
            this.cdr.detectChanges();
            return;
          }
          this.data = res;
          this.loading = false;
          this.hisG2 = res.hisG2;
          this.hisTop = res.hisTop;

          this.itemCount = res.itemData.count;
          this.itemWeek = res.itemData.week;
          this.itemLastWeek = res.itemData.lastWeek;
          this.itemToday = res.itemData.today;
          this.itemYesterday = res.itemData.yesterday;

          this.userCount = res.userData.count;
          this.userWeek = res.userData.week;
          this.userLastWeek = res.userData.lastWeek;
          this.userToday = res.userData.today;
          this.userYesterday = res.userData.yesterday;

          const data = res.itemG2;
          const x = data.map(function (item) {
            return item.x;
          });
          const y1 = data.map(function (item) {
            return item.y1;
          });
          const y2 = data.map(function (item) {
            return item.y2;
          });
          this.initItemChat(x, y1, y2);
          this.cdr.detectChanges();
        }
      );
  }

  initItemChat(x, y1, y2) {
    this.option = {
      title: {
        text: '商品下架上架30天内趋势图'
      },
      tooltip: {
        trigger: 'axis',
        axisPointer: {
          type: 'cross',
          label: {
            backgroundColor: '#eab72e'
          }
        }
      },
      legend: {
        data: ['上架', '下架']
      },
      toolbox: {
        feature: {
          saveAsImage: {}
        }
      },
      grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
      },
      xAxis: [
        {
          type: 'category',
          boundaryGap: false,
          data: x
        }
      ],
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        {
          name: '上架',
          type: 'line',
          stack: '总量',
          areaStyle: {},
          data: y1
        },
        {
          name: '下架',
          type: 'line',
          stack: '总量',
          areaStyle: {},
          data: y2
        }
      ]
    };
  }

  calculateRatio(now, last) {
    if (last === 0) {
      return '基数为零';
    }
    if (now < last) {
      const num: number = (last - now) / last * 100;
      return num.toFixed(2) + '%';
    } else {
      const num: number = (now - last) / last * 100;
      return num.toFixed(2) + '%';
    }
  }

  setDate(type: any) {
    this.date_range = getTimeDistance(type);
    this.changeVisitG2(this.date_range);
    setTimeout(() => this.cdr.detectChanges());
  }
}
