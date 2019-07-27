import { Component, OnInit, ViewChild, Inject, ElementRef } from '@angular/core';
import { _HttpClient } from '@delon/theme';
import { NzFormatEmitEvent, NzMessageService, UploadFile } from 'ng-zorro-antd';
import { STColumn, STComponent, STChange } from '@delon/abc';
import { ContentListViewComponent } from './view/view.component';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TokenService, DA_SERVICE_TOKEN } from '@delon/auth';

@Component({
  selector: 'app-content-list',
  templateUrl: './list.component.html',
})
export class ContentListComponent implements OnInit {


  category = '';
  isVisible = false;
  url = `/user/content/query/list`;

  params: any = {
    'categoryId': 1,
  };
  scroll = { x: '1300px' };

  checkId = [];

  @ViewChild('st') comp: STComponent;

  columns: STColumn[] = [
    { title: 'id', index: 'contentId', type: 'checkbox', fixed: 'left' },
    { title: '类目Id', index: 'conCatId', width: '80px', fixed: 'left' },
    {
      title: '内容标题',
      fixed: 'left',
      index: 'contentTitle',
      width: '150px',
    },
    {
      title: '内容副标题',
      index: 'subTitle',
      width: '150px',
    },
    { title: '标题描述', index: 'titleDesc', width: '120px' },
    { title: '内容导航的url', index: 'contentUrl', width: '170px' },
    {
      title: '内容图片Url',
      index: `contentPic`,
      width: '270px',
    },
    { title: '发布时间', index: 'contentCreate', type: 'date', width: '150px' },
    {
      title: '操作',
      width: '170px',
      fixed: 'right',
      buttons: [
        {
          text: '查看',
          type: 'drawer',
          drawer: {
            title: '内容详情',
            component: ContentListViewComponent
          },
          click: (record: any, modal: any) =>
            this.message.success(
              `重新加载页面，回传值：${JSON.stringify(modal)}`,
            ),
        },
        {
          text: '删除',
          pop: true,
          popTitle: '确定删除码？ ',
          click: (content: any) => {
            this.message.info('后台功能未实现');
            // this.http.get<any>('').subscribe(
            //   res => {
            //     if (res.status === 200) {

            //     } else {
            //       this.message.error('删除失败');
            //     }
            //   }
            // );
            // console.log(content.contentId);
          },
        },
        {
          text: '更改',
          pop: true,
          disable: true,
          click: (content: any) => {
            this.message.info('后台功能未实现');
            // this.http.get<any>('').subscribe(
            //   res => {
            //     if (res.status === 200) {

            //     } else {
            //       this.message.error('删除失败');
            //     }
            //   }
            // );
            // console.log(content.contentId);
          },
        },
      ],
    },
  ];

  // 内容表单
  validateForm: FormGroup;

  // 图片
  previewImage = '';
  previewVisible = false;
  fileList = [];

  constructor(private http: _HttpClient, private message: NzMessageService, private fb: FormBuilder) { }

  nodesflag = false;
  nodes = [];

  ngOnInit() {
    this.contentTree(0);
    this.nodesflag = true;
  }

  handlePreview = (file: UploadFile) => {
    this.previewImage = file.url || file.thumbUrl;
    this.previewVisible = true;
  }

  showModel() {
    if (this.params.categoryId === 0) {
      this.message.error('请于左侧选择增加内容的类别');
    } else {
      this.validateForm = this.fb.group({
        conCatId: [this.params.categoryId, [Validators.required]],
        contentTitle: [null, [Validators.required]],
        subTitle: [null],
        titleDesc: [null],
        contentPic: [null],
        contentUrl: [null],
        content: [null],
      });
      this.isVisible = true;
    }
  }

  nzEvent(event: NzFormatEmitEvent): void {
    if (event.eventName === 'expand') {
      if (event.node.getChildren().length === 0 && event.node.isExpanded) {
        this.http.get<any>('/user/content/category/list', { 'id': event.node.key }).subscribe(
          res => {
            event.node.addChildren(res);
          }
        );
      }
    }
  }

  change(e: STChange) {
    if (e.checkbox != null) {
      this.checkId = [];
      for (let i = 0; i < e.checkbox.length; i++) {
        this.checkId.push(e.checkbox[i].contentId);
      }
    }

  }

  deleteContentList() {
    this.message.info('点击delete: ' + this.checkId);
  }


  loadContent(event: NzFormatEmitEvent): void {
    this.params = {
      'categoryId': event.node.key,
    };
    this.category = event.node.title;
    this.comp.reset(this.params);
  }

  contentTree(index) {
    this.http.get<any>('/user/content/category/list', { 'id': index }).subscribe(
      res => {
        this.nodes = res;
        return res;
      }
    );
  }

  handleOk(): void {
    // tslint:disable-next-line:forin
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();
    }
    if (this.validateForm.valid) {
      let fileString = '';
      for (let i = 0; i < this.fileList.length; i++) {
        fileString = fileString + this.fileList[i].response.data;
        if (i !== this.fileList.length - 1) {
          fileString = fileString + ',';
        }
      }
      this.validateForm.patchValue({ contentPic: fileString });
      console.log(this.validateForm.value);
      this.http.post<{}>('/user/content/save', this.validateForm.value).subscribe(
        res => {
          console.log(res);
        }
      );
      this.isVisible = false;
    } else {
      this.message.error('标题必填！');
    }
  }

  handleCancel(): void {
    this.isVisible = false;
  }

  removeFile = (file: UploadFile) => {
    return false;
  }

}
