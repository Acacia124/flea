import { Component, ChangeDetectionStrategy, OnInit } from '@angular/core';
import { _HttpClient } from '@delon/theme';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { ProSettingSecurityModelPasswordComponent } from './model/password/password.component';
import { ProSettingSecurityModelEmailComponent } from './model/email/email.component';

@Component({
  selector: 'app-account-settings-security',
  templateUrl: './security.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProAccountSettingsSecurityComponent implements OnInit {
  loading = false;
  data = [];

  mail;

  constructor(
    private http: _HttpClient,
    private msg: NzMessageService,
    private modalService: NzModalService
  ) {
   }

  ngOnInit(): void {
    this.loading = true;
    this.http.get<any>('/user/my').subscribe(
        res => {
          this.loading = false;
          if (res.status === 200) {
            this.mail = res.data.email;
            this.data = [
              {
                id: 1,
                title: '账户密码',
                desc: `当前密码强度：强`
              },
              {
                id: 2,
                title: '绑定邮箱',
                desc: `已绑定邮箱：${res.data.email}`
              }
            ];
          }
        }
      );
  }

  changeEmail(id: number) {
    if (id !== 2) {
      this.msg.error('该功能已废弃...');
      return;
    }
    const modal = this.modalService.create({
      nzTitle: '更改登陆邮箱',
      nzMaskClosable: false,
      nzContent: ProSettingSecurityModelEmailComponent,
      nzWidth: 450,
      nzFooter: null,
      nzComponentParams: {
        emailOld: this.mail
      },
    });
  }
}
