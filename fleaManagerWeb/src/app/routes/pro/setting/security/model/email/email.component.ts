import { Component, OnInit, Inject, Input } from '@angular/core';
import { NzModalRef, NzMessageService, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DA_SERVICE_TOKEN, ITokenService, TokenService } from '@delon/auth';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-pro-setting-security-model-email',
  templateUrl: './email.component.html',
  styles: [
    `
      .steps-content {
        margin-top: 16px;
        border: 1px dashed #e9e9e9;
        border-radius: 6px;
        background-color: #fafafa;
        min-height: 120px;
        text-align: center;
        padding-top: 20px;
        padding-left: 10px;
        padding-right: 10px;
      }

      .steps-action {
        margin-top: 24px;
      }

      button {
        margin-right: 8px;
      }
    `
  ]
})
export class ProSettingSecurityModelEmailComponent implements OnInit {
  count = 0;
  interva$: any;
  count2 = 0;
  interva$2: any;
  current = 0;
  flag = false;

  codeFirst = null;
  codeSecond = null;
  form: FormGroup;
  // 接受用户之前邮箱
  @Input() emailOld: string;
  emailNew: string;

  constructor(private fb: FormBuilder, private http: _HttpClient,
    @Inject(DA_SERVICE_TOKEN) private tokenService: ITokenService,
    public msg: NzMessageService, private ref: NzModalRef) {
    this.form = fb.group({
      mail: [null, [Validators.required, Validators.email]],
      captcha: [null, [Validators.required, Validators.minLength(6)]]
    });
  }

  // 请求头
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  get mail() {
    return this.form.controls.mail;
  }
  get captcha() {
    return this.form.controls.captcha;
  }

  ngOnInit(): void {
  }

  pre(): void {
    this.current -= 1;
  }

  next(): void {
    if (this.current === 0) {
      // 邮箱校验
      if (!this.flag) {
        this.http.post<any>('/login/mail', this.httpOptions, { mail: this.emailOld, code: this.codeFirst })
          .subscribe(
            res => {
              if (res.status === 200) {
                this.flag = true;
                this.current += 1;
              } else {
                this.msg.error(res.msg);
                return;
              }
            }
          );
      } else {
        this.current += 1;
      }
    }
  }

  done(): void {
    this.captcha.markAsDirty();
    this.captcha.updateValueAndValidity();
    if (this.mail.invalid || this.captcha.invalid) return;
    // 更改
    this.http.post<any>('/user/reset', this.httpOptions, { email: this.mail.value, code: this.captcha.value })
      .subscribe(
        res => {
          // 更新成功更新本地缓存信息
        }
      );
  }

  getCaptcha(mail: string) {
    if (mail === this.emailOld) {
      this.count = 59;
      this.count -= 1;
      this.interva$ = setInterval(() => {
        this.count -= 1;
        if (this.count <= 0) clearInterval(this.interva$);
      }, 1000);
      this.sendMail(mail);
    } else {
      if (!this.mail.valid) {
        this.msg.error('请输入正确的邮箱格式！');
      } else {
        this.count2 = 59;
        this.count2 -= 1;
        this.interva$2 = setInterval(() => {
          this.count2 -= 1;
          if (this.count2 <= 0) clearInterval(this.interva$2);
        }, 1000);
      }
      this.sendMail(mail);
    }
  }

  // sendCode
  sendMail(mail: any) {
    // tslint:disable-next-line:max-line-length
    this.http.post<any>
      ('/login/sendMailCode', this.httpOptions, {
        to: mail,
        title: '【物不如故】更换密保邮箱请求',
        userName: this.tokenService.get().name,
        type: '邮箱验证'
      }).subscribe(
        res => {
          if (res.status === 200) {
            console.log(res.data);
          } else {
            this.msg.error(res.msg);
            return;
          }
        }
      );
  }

}
