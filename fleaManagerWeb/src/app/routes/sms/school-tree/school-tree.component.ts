import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { NzMessageService } from 'ng-zorro-antd';
import { map } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-sms-school-tree',
  templateUrl: './school-tree.component.html',
})
export class SmsSchoolTreeComponent implements OnInit {

  constructor(private http: _HttpClient, private message: NzMessageService) { }

  schoolList: any[];

  isVisible = false;
  isOkLoading = false;

  isVisible2 = false;
  isOkLoading2 = false;

  loding = true;

  upSchool = {
    schoolId: -1,
    schoolName: ''
  };

  addSchoolName = '';

  // 请求头
httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

  ngOnInit() {
    this.initSchoolList();
  }

  add(): void { }

  initSchoolList(): void {
    this.http
      .get('/school/list')
      .pipe(
        map((list: any) =>
          list.map(i => {
            i.deStatus = false;
            return i;
          }),
        ),
      )
      .subscribe(res => {
        this.schoolList = res; this.loding = false;
      });
  }

  deletaSchool(school: any): void {
    school.deStatus = true;
    this.http.get<{ status: any, msg: any }>('/user/school/delete', { id: school.schoolId })
      .subscribe(res => {
        if (res.status === 200) {
          this.message.success(`成功删除该地址`);
          this.schoolList = this.schoolList.filter(x => x.schoolId !== school.schoolId);
        } else {
          this.message.error(`删除错误[${res.msg}]`);
        }
        school.deStatus = false;
      });
  }

  showModal(school: any): void {
    this.upSchool.schoolId = school.schoolId;
    this.upSchool.schoolName = school.schoolName;
    this.isVisible = true;
  }

  handleOk(): void {
    this.isOkLoading = true;
    if ( this.upSchool.schoolName === null || this.upSchool.schoolName === '') {
      this.message.error('键入正确地址');
    } else {
      for (let i = 0; i < this.schoolList.length; i++) {
        if (this.schoolList[i].schoolName === this.upSchool.schoolName) {
          this.message.error('该地址已存在');
          this.isVisible = false;
          this.isOkLoading = false;
          return ;
        }
      }
      this.http
      .post<{ status: any, msg: any }>('/user/address/update', this.httpOptions, this.upSchool )
      .subscribe(
        res => {
          if (res.status === 200) {
            for (let i = 0; i < this.schoolList.length; i++) {
              if (this.schoolList[i].schoolId === this.upSchool.schoolId) {
                this.schoolList[i].schoolName = this.upSchool.schoolName;
                this.message.success(`修改地址成功`);
              }
            }
          } else {
            this.message.error(`修改地址错误`);
          }
          this.isVisible = false;
          this.isOkLoading = false;
        }
      );
    }
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  showModal2(school: any): void {
    this.isVisible2 = true;
  }

  handleOk2(): void {
    this.isOkLoading2 = true;
    if ( this.addSchoolName === null || this.addSchoolName === '') {
      this.message.error('键入正确地址');
      this.isVisible2 = false;
      this.isOkLoading2 = false;
      return ;
    } else {
      for (let i = 0; i < this.schoolList.length; i++) {
        if (this.schoolList[i].schoolName === this.addSchoolName) {
          this.message.error('该地址已存在');
          this.isVisible2 = false;
          this.isOkLoading2 = false;
          return ;
        }
      }
      this.http
      .post<{ status: any, msg: any }>('/user/address/add', this.httpOptions, { schoolName: this.addSchoolName } )
      .subscribe(
        res => {
          if (res.status === 200) {
              this.schoolList.push({schoolName: this.addSchoolName, schoolId: res.msg});
              this.message.success(`增加地址成功`);
          } else {
            this.message.error(`增加地址失败`);
          }
          this.isVisible2 = false;
          this.isOkLoading2 = false;
        }
      );
    }
  }

  handleCancel2(): void {
    this.isVisible2 = false;
  }
}
