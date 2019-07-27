import { SettingsService, _HttpClient } from '@delon/theme';
import { Component, OnDestroy, Inject, Optional } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import {
  SocialService,
  SocialOpenType,
  TokenService,
  DA_SERVICE_TOKEN,
} from '@delon/auth';
import { ReuseTabService } from '@delon/abc';
import { environment } from '@env/environment';
import { StartupService } from '@core/startup/startup.service';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Component({
  selector: 'passport-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.less'],
  providers: [SocialService],
})
export class UserLoginComponent implements OnDestroy {

  constructor(
    fb: FormBuilder,
    private router: Router,
    public msg: NzMessageService,
    private modalSrv: NzModalService,
    private settingsService: SettingsService,
    private socialService: SocialService,
    public http: _HttpClient,
    @Optional()
    @Inject(ReuseTabService)
    private reuseTabService: ReuseTabService,
    @Inject(DA_SERVICE_TOKEN) private tokenService: TokenService,
    private startupSrv: StartupService,
  ) {
    this.form = fb.group({
      userName: [null, [Validators.required, Validators.minLength(5)]],
      password: [null, Validators.required],
      mail: [null, [Validators.required, Validators.email]],
      captcha: [null, [Validators.required, Validators.minLength(6)]],
      remember: [true],
    });
    modalSrv.closeAll();
  }

  code = 0;
  // region: fields

  get userName() {
    return this.form.controls.userName;
  }
  get password() {
    return this.form.controls.password;
  }
  get mail() {
    return this.form.controls.mail;
  }
  get captcha() {
    return this.form.controls.captcha;
  }

  // 请求头
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  form: FormGroup;
  error = '';
  type = 1;
  loading = false;

  // region: get captcha

  // tslint:disable-next-line:member-ordering
  count = 0;
  interval$: any;

  // endregion

  switch(ret: any) {
    this.type = ret.index;
  }

  getCaptcha() {
    if (!this.mail.valid) {
      this.msg.error('请输入正确的邮箱格式！');
    } else {
      this.count = 59;
      this.interval$ = setInterval(() => {
        this.count -= 1;
        if (this.count <= 0) clearInterval(this.interval$);
      }, 1000);
      this.http.post<any>('/login/mail/check', this.httpOptions, { mail: this.mail.value })
        .subscribe(
          res => {
            if (res.status === 200) {
              this.sendMail(res.data);
            } else {
              this.error = '用户尚未注册';
              return;
            }
          }
        );
    }
  }
  // sendCode
  sendMail(name: any) {
    // tslint:disable-next-line:max-line-length
    this.http.post<any>
      ('/login/sendMailCode', this.httpOptions, { to: this.mail.value, title: '【物不如故】登录请求', userName: name, type: '登录验证' }, )
      .subscribe(
        res => {
          if (res.status === 200) {
            this.code = res.data;
          } else {
            this.msg.error(res.msg);
            return;
          }
        }
      );
  }

  // endregion

  submit() {
    this.error = '';
    if (this.type === 0) {
      this.userName.markAsDirty();
      this.userName.updateValueAndValidity();
      this.password.markAsDirty();
      this.password.updateValueAndValidity();
      if (this.userName.invalid || this.password.invalid) return;
    } else {
      this.mail.markAsDirty();
      this.mail.updateValueAndValidity();
      this.captcha.markAsDirty();
      this.captcha.updateValueAndValidity();
      if (this.mail.invalid || this.captcha.invalid) return;
    }

    // **注：** DEMO中使用 `setTimeout` 来模拟 http
    // 默认配置中对所有HTTP请求都会强制[校验](https://ng-alain.com/auth/getting-started) 用户 Token
    // 然一般来说登录请求不需要校验，因此可以在请求URL加上：`/login?_allow_anonymous=true` 表示不触发用户 Token 校验
    this.loading = true;
    this.loading = false;
    // if (this.type === 0) {
    //   if (
    //     this.userName.value !== 'admin' ||
    //     this.password.value !== '888888'
    //   ) {
    //     this.error = `账户或密码错误`;
    //     return;
    //   }
    // }
    if (this.type === 1) {
      // 清空路由复用信息
      this.reuseTabService.clear();
      // 设置Token信息
      this.http.post<any>('/login/mail', this.httpOptions, { mail: this.mail.value, code: this.captcha.value })
        .subscribe(
          res => {
            this.tokenService.set({
              token: res.data.token,
              name: res.data.nickName,
              email: res.data.email,
              id: res.data.openid,
              avatarUrl: res.data.avatarUrl,
              time: +new Date(),
            });
            // 重新获取 StartupService 内容，若其包括 User 有关的信息的话
            this.startupSrv.load().then(() => this.router.navigate(['/']));
          }
        );
    }
  }

  // region: social
  open(type: string, openType: SocialOpenType = 'href') {
    let url = ``;
    let callback = ``;
    if (environment.production)
      callback = 'https://ng-alain.github.io/ng-alain/callback/' + type;
    else callback = 'http://localhost:4200/callback/' + type;
    switch (type) {
      case 'auth0':
        url = `//cipchk.auth0.com/login?client=8gcNydIDzGBYxzqV0Vm1CX_RXH-wsWo5&redirect_uri=${decodeURIComponent(
          callback,
        )}`;
        break;
      case 'github':
        url = `//github.com/login/oauth/authorize?client_id=9d6baae4b04a23fcafa2&response_type=code&redirect_uri=${decodeURIComponent(
          callback,
        )}`;
        break;
      case 'weibo':
        url = `https://api.weibo.com/oauth2/authorize?client_id=1239507802&response_type=code&redirect_uri=${decodeURIComponent(
          callback,
        )}`;
        break;
    }
    if (openType === 'window') {
      this.socialService
        .login(url, '/', {
          type: 'window',
        })
        .subscribe(res => {
          if (res) {
            this.settingsService.setUser(res);
            this.router.navigateByUrl('/');
          }
        });
    } else {
      this.socialService.login(url, '/', {
        type: 'href',
      });
    }
  }

  // endregion

  ngOnDestroy(): void {
    if (this.interval$) clearInterval(this.interval$);
  }
}
