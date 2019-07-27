import { Component, OnInit, Input } from '@angular/core';
import { NzModalRef, NzMessageService, NzDrawerRef } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'app-sms-user-list-view',
  templateUrl: './view.component.html',
})
export class SmsUserListViewComponent implements OnInit {
  @Input()
  record: any = {};

  itemList: any = {
    rows: [],
    total: 0,
  };

  params: any = {
    page: 1,
    rows: 5,
    userId: this.record.openid
  };

  constructor(
    private ref: NzDrawerRef,
    public http: _HttpClient,
    public msg: NzMessageService
  ) { }

  ngOnInit(): void {
    this.params.userId = this.record.openid;
    this.http.get('/user/item/list', this.params)
        .subscribe(
          res => {
            this.itemList = res;
          }
        );
  }

  changeIndex(): void {
    this.http.get('/user/item/list', this.params)
        .subscribe(
          res => {
            this.itemList = res;
          }
      );
  }

  close() {
    this.ref.close();
  }
}
