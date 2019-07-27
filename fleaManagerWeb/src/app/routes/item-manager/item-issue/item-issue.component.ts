import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { NzFormatEmitEvent, NzMessageService, UploadFile } from 'ng-zorro-antd';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { differenceInCalendarDays } from 'date-fns';

@Component({
  selector: 'app-item-manager-item-issue',
  templateUrl: './item-issue.component.html'
})
export class ItemManagerItemIssueComponent implements OnInit {

  form: FormGroup;
  submitting = false;
  addressList = [];
  nodes = [];
  today = new Date();
  // 图片
  previewImage = '';
  previewVisible = false;
  fileList = [];

  constructor(private fb: FormBuilder, private msg: NzMessageService, private http: _HttpClient) { }

  ngOnInit(): void {
    this.initSchoolList();
    this.initCatList();
    this.form = this.fb.group({
      itemTitle: [null, [Validators.required]],
      sellPoint: [null, [Validators.required]],
      price: [null, [Validators.required]],
      originalPrice: [null, [Validators.required]],
      sellAddress: [null, [Validators.required]],
      catId: [null, [Validators.required]],
      newnessRate: [88, [Validators.required]],
      buyTime: [null, [Validators.required]],
      sellQq: [null],
      wechat: [null],
      itemImage: [null],
      sellPhone: [null],
      status: [1, [Validators.min(1), Validators.max(2)]],
    });
  }

  initSchoolList(): void {
    this.http
      .get<any>('/school/list')
      .subscribe(res => {
        this.addressList = res;
      });
  }

  initCatList(): void {
    this.http.get<[{}]>('/item/cat/list/')
      .subscribe(
        res => {
          this.nodes = res;
        }
      );
  }

  onExpandChange(event: NzFormatEmitEvent): void {
    if (event.node.getChildren().length === 0 && event.node.isExpanded) {
      this.http.get<any>('/item/cat/list/', { 'id': event.node.key }).subscribe(
        res => {
          event.node.addChildren(res);
        }
      );
    }
  }

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, this.today) > 0;
  }

  removeFile = (file: UploadFile) => {
    console.log(JSON.stringify(file));
    // this.http.get<any>('/user/pic/delete?fileName=' + fileName)
    //   .subscribe(
    //     res => {
    //       if (res.status === 200) {
    //         if (res.data === -1) {
    //           this.msg.error('删除失败！');
    //         } else if (res.data === 2) {
    //           this.msg.error('图片不存在');
    //         } else {

    //         }
    //       } else {
    //         this.msg.error(res.msg);
    //       }
    //     }
    //   );
  }

  submit() {
    this.submitting = true;
    setTimeout(() => {
      this.submitting = false;
      this.msg.success(`提交成功`);
    }, 1000);
  }

  handlePreview = (file: UploadFile) => {
    this.previewImage = file.url || file.thumbUrl;
    this.previewVisible = true;
  }
}
