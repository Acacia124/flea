import { Component, OnInit } from '@angular/core';
import * as distanceInWordsToNow from 'date-fns/distance_in_words_to_now';
import { NzMessageService } from 'ng-zorro-antd';
import { NoticeItem, NoticeIconList } from '@delon/abc';
import { _HttpClient } from '@delon/theme';
import { Router } from '@angular/router';

/**
 * 菜单通知
 */
@Component({
  selector: 'header-notify',
  template: `
  <notice-icon
    [data]="data"
    [count]="count"
    [loading]="loading"
    (select)="select($event)"
    (clear)="clear($event)"
    (popoverVisibleChange)="loadData()"></notice-icon>
    <nz-modal [nzStyle]="{ right: '150px',top: '65px' }" [(nzVisible)]="isVisible"
          [nzTitle]="nztitle" [nzFooter]="footerButton" (nzOnCancel)="handleCancel()">
      <ng-template style="text-align:center" #nztitle>
        <nz-avatar nzSize="small" [nzSrc]="message?.messageOperatorAvater"></nz-avatar>
        <span style="margin-left:10px;font-size: 10px;">{{message?.messageOperatorNick}}</span>
      </ng-template>
      <div>
        标题：{{message?.messageTitle}}
        <br/>
        <br/>
        内容：{{message?.messageTxt}}
      </div>
      <ng-template style="text-align:center" #footerButton>
      <button nz-button (click)="reply(message)" [disabled]="message?.messageOperator==='001'?true:false">回复</button>
      </ng-template>
    </nz-modal>
  `,
})
export class HeaderNotifyComponent implements OnInit {
  isVisible = false;
  message: any;
  data: NoticeItem[] = [
    {
      title: '通知',
      list: [],
      emptyText: '你已查看所有通知',
      emptyImage:
        'https://gw.alipayobjects.com/zos/rmsportal/wAhyIChODzsoKIOBHcBk.svg',
      clearText: '清空通知',
    },
    {
      title: '消息',
      list: [],
      emptyText: '你已读完所有消息',
      emptyImage:
        'https://gw.alipayobjects.com/zos/rmsportal/sAuJeJzSKbUmHfBQRzmZ.svg',
      clearText: '清空消息',
    },
    {
      title: '警告',
      list: [],
      emptyText: '你已处理完所有警告',
      emptyImage:
        'https://gw.alipayobjects.com/zos/rmsportal/HsIsxMZiWKrNUavQUXqx.svg',
      clearText: '清空系统警告',
    }
  ];
  count = 0;
  loading = false;

  constructor(private msg: NzMessageService, public http: _HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.initNoReadCount();
  }

  // 初始化未读信息count
  initNoReadCount() {
    this.http.get<{ status: any, msg: any, data: any }>('/user/message').subscribe(
      res => {
        this.count = res.data;
      }
    );
  }

  updateNoticeData(notices: NoticeIconList[]): NoticeItem[] {
    const data = this.data.slice();
    data.forEach(i => (i.list = []));

    notices.forEach(item => {
      const newItem = { ...item };
      if (newItem.datetime)
        newItem.datetime = distanceInWordsToNow(item.datetime, {
          locale: (window as any).__locale__,
        });

      data.find(w => w.title === newItem.type).list.push(newItem);
    });
    return data;
  }

  loadData() {
    if (this.loading) return;
    this.loading = true;
    this.http.get<{ status: any, msg: any, data: any[] }>('/user/message/noread').subscribe(
      res => {
        this.data = this.updateNoticeData(res.data);
        this.loading = false;
      }
    );
  }

  reply(msg) {
    this.router.navigate(['/message/station-letter'], {
      queryParams: {
        msg: JSON.stringify(msg)
      }
    });
  }

  clear(type: string) {
    this.http.get<{ status: any, msg: any, data: any[] }>('/user/message/all/read', { type: type }).subscribe(
      res => {
        if (res.status === 200) {
          if (type === '通知') {
            this.data[0].list = [];
          } else if (type === '消息') {
            this.data[1].list = [];
          } else if (type === '警告') {
            this.data[2].list = [];
          }
          this.msg.success(`清空了 ${type} `);
        } else {
          this.msg.error(`清空失败`);
        }
      }
    );
  }

  select(res: any) {
    this.http.get<{ status: any, msg: any, data: any[] }>('/user/message/info', { messageInfoId: res.item.id })
      .subscribe(
        result => {
          this.message = result.data;
          res.read = true;
          this.isVisible = true;
          if (this.count > 0) {
            this.count--;
          }
        }
      );
  }

  handleCancel(): void {
    this.isVisible = false;
  }
}
