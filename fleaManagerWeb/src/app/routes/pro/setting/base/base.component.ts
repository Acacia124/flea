import {
  Component,
  ChangeDetectionStrategy,
  OnInit,
  ChangeDetectorRef,
  Inject,
} from '@angular/core';
import { _HttpClient } from '@delon/theme';
import { NzMessageService, UploadFile } from 'ng-zorro-antd';
import { DA_SERVICE_TOKEN, TokenService } from '@delon/auth';
import { StartupService } from '@core/startup/startup.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-settings-base',
  templateUrl: './base.component.html',
  styleUrls: ['./base.component.less'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProAccountSettingsBaseComponent implements OnInit {
  avatar = '';
  userLoading = true;
  user: any;

  constructor(
    private startupSrv: StartupService,
    private router: Router,
    @Inject(DA_SERVICE_TOKEN) private tokenService: TokenService,
    private http: _HttpClient,
    private cdr: ChangeDetectorRef,
    private msg: NzMessageService,
  ) { }

  ngOnInit(): void {
    this.http.get<any>('/user/my')
      .subscribe(
        res => {
          this.userLoading = false;
          if (res.status === 200) {
            this.user = res.data;
          }
          this.cdr.detectChanges();
        }
      );
  }


  handleChange(info: { file: UploadFile }): void {
    if (info.file.response.status === 200) {
      // 删除之前用户头像
      this.http.get<any>('/user/pic/delete?fileName=' + this.user.avatarUrl).subscribe();
      this.user.avatarUrl = info.file.response.data;
      this.updateUserInfo(this.user);
    } else {
      this.msg.error('上传失败：', info.file.response.msg);
    }
  }

  updateUserInfo(user) {
    this.http.post<any>('/user/reset', user)
    .subscribe(
      res => {
        console.log(res);
        // 更新本地缓存
        this.tokenService.set({
          token: res.data,
          name: user.nickName,
          email: user.email,
          id: this.tokenService.get().id,
          avatarUrl: user.avatarUrl,
          time: + new Date(),
        });
        this.startupSrv.load().then(() => this.router.navigate(['/pro/settings/base']));
      }
    );
  }
  save() {
    this.updateUserInfo(this.user);
    return false;
  }
}
