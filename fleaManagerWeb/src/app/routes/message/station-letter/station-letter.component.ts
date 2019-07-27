import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent, STChange, STColumnTag } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { of, Observable, BehaviorSubject } from 'rxjs';
import { delay, map, debounceTime, switchMap } from 'rxjs/operators';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd';
import { ActivatedRoute } from '@angular/router';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-message-station-letter',
  templateUrl: './station-letter.component.html',
})
export class MessageStationLetterComponent implements OnInit {
  users: any[] = [];
  selectedIndex = 0;
  isVisible = false;
  message: any;
  // 收件人
  listOfOption = [];
  // 收件人列表
  listOfSelectedValue = [];
  userPrincipal = 'D';
  msgTitle: string;
  msgType = '消息';
  msgContent: string;
  msgMail = false;
  a = false;
  b = false;
  c = false;
  isLoading = false;
  searchChange$ = new BehaviorSubject('');


  // 请求头
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private activatedRoute: ActivatedRoute, private http: _HttpClient, public msg: NzMessageService) {
  }
  columns: STColumn[] = [
    {
      title: '编号',
      index: 'id',
      type: 'checkbox'
    },
    {
      title: '标题',
      index: 'title',
      width: '350px'
    },
    {
      title: '信息类型',
      index: 'type',
      width: '150px',
      type: 'tag',
      // tslint:disable-next-line: no-use-before-declare
      tag: TAG,
      filter: {
        menus: [
          { text: '警告', value: '警告' },
          { text: '通知', value: '通知' },
          { text: '消息', value: '消息' }
        ],
        fn: (filter: any, record: any) =>
          record.type === filter.value,
        multiple: true,
      },
    },
    {
      title: '发件人',
      index: 'nickName'
    },
    {
      title: '发件时间',
      index: 'datetime',
      type: 'date',
      sort: {
        compare: (a, b) => {
          const date1 = Date.parse(new Date(a.datetime).toString());
          const date2 = Date.parse(new Date(b.datetime).toString());
          return date1 - date2;
        },
      },
    }
  ];

  ngOnInit(): void {
    // 初始化信息列表
    this.changeMessage(0);
    // 初始化收件人
    this.searchUser();

    this.activatedRoute.queryParams.subscribe(queryParams => {
      if (queryParams !== null && queryParams.msg !== null && queryParams.msg !== undefined) {
        this.reply(JSON.parse(queryParams.msg));
      }
    });
  }

  // tables变更函数
  changeMessage(index) {
    this.users = [];
    if (index === 0) {
      this.http.get<any>('/user/message/noread')
        .subscribe(
          res => {
            if (res.status === 200) {
              this.users = res.data;
            } else {
              this.msg.error(res.msg);
            }
          }
        );
    } else if (index === 1) {
      this.http.get<any>('/user/message/read')
        .subscribe(
          res => {
            if (res.status === 200) {
              this.users = res.data;
            } else {
              this.msg.error(res.msg);
            }
          }
        );
    }
  }

  select(res: any) {
    if (!this.isVisible) {
      this.http.get<{ status: any, msg: any, data: any[] }>('/user/message/info', { messageInfoId: res.id })
        .subscribe(
          result => {
            this.message = result.data;
            this.isVisible = true;
            if (this.selectedIndex === 0) {
              this.users = this.users.filter(user => user.id !== res.id);
            }
          }
        );
    }
  }

  _click(e: STChange) {
    this.select(e.click.item);
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  reply(msg) {
    this.handleCancel();
    this.selectedIndex = 2;
    this.listOfSelectedValue = [msg.messageOperator];
    this.userPrincipal = 'D';
    const option = {
      label: msg.messageOperatorNick,
      value: msg.messageOperator
    };
    if (this.listOfOption.filter(op => op.value === msg.messageOperator).length === 0) {
      this.listOfOption.push(option);
    }
  }

  // 发送站内信
  // 搜索用户
  onSearch(value: string): void {
    this.isLoading = true;
    this.searchChange$.next(value);
  }

  searchUser() {
    const getRandomNameList = (name: string) =>
      this.http
        .get('/user/getUserLike/' + name)
        .pipe(map((res: any) => res.data))
        .pipe(
          map((list: any) => {
            return list.map((item: any) => item);
          })
        );
    const optionList$: Observable<any> = this.searchChange$
      .asObservable()
      .pipe(debounceTime(500))
      .pipe(switchMap(getRandomNameList));
    optionList$.subscribe(data => {
      this.listOfOption = data;
      this.isLoading = false;
    });
  }

  submitForm(): void {
    if (this.msgTitle === null || this.msgTitle === undefined || this.msgTitle.trim() === '') {
      this.b = true;
    } else {
      console.log('title: ', this.msgTitle);
      this.b = false;
    }
    if (this.userPrincipal === 'D' && this.listOfSelectedValue.length === 0) {
      this.a = true;
    } else {
      console.log('listOfSelectedValue: ', this.listOfSelectedValue);
      this.a = false;
    }
    if (this.msgContent === null || this.msgContent === undefined || this.msgContent.trim() === '') {
      this.c = true;
    } else {
      console.log('msgContent: ', this.msgContent);
      this.c = false;
    }
    if (this.a || this.b || this.c) {
      return;
    } else {
      const info = {
        messageTitle: this.msgTitle,
        messageTxt: this.msgContent,
        messageType: this.msgType
      };
      let params = {};
      let url = '';
      if (this.userPrincipal === 'D') {
        params = {
          openIds: JSON.stringify(this.listOfSelectedValue),
          info: JSON.stringify(info),
          isSystem: this.msgMail
        };
        url = '/user/message/all/send';
      } else {
        params = {
          status: this.userPrincipal,
          info: JSON.stringify(info),
          isSystem: this.msgMail
        };
        url = '/user/message/all/sendByStatus';
      }
      this.http.post<any>(url, params).subscribe(
        res => {
          if (res.status === 200) {
            this.msg.success('发送成功');
          } else {
            this.msg.error(`发送失败：${res.msg}`);
          }
        }
      );
    }
  }
}


const TAG: STColumnTag = {
  '消息': { text: '消息', color: 'blue' },
  '通知': { text: '通知', color: '' },
  '警告': { text: '警告', color: 'orange' },
};
