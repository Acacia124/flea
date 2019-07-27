import { Component, OnInit, Input, Inject } from '@angular/core';
import { NzModalRef, NzMessageService, UploadFile, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { setHours, differenceInCalendarDays } from 'date-fns';
import { Data } from '@angular/router';
import { STColumn } from '@delon/abc';
import { map } from 'rxjs/operators';
import { ITokenService, DA_SERVICE_TOKEN } from '@delon/auth';
import { HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-item-manager-item-list-item-info',
  templateUrl: './item-info.component.html',
  styleUrls: ['./item-info.component.css']
})
export class ItemManagerItemListItemInfoComponent implements OnInit {


  constructor(private fb: FormBuilder, private http: _HttpClient,
    @Inject(DA_SERVICE_TOKEN) private tokenService: ITokenService,
    public msg: NzMessageService, private ref: NzModalRef) { }
  // 接受商品Id
  @Input() itemId: string;
  // 分类编号
  @Input() catLists: any;

  edit = '编辑';

  catId: string;

  itemInfo: Item;

  loding = false;

  user: any;

  message: any;

  today = new Date();

  itemList: any;

  params: any = {
    page: 1,
    rows: 5,
    userId: null
  };

  fileList = [
  ];

  adminflag = false;
  adminPermission = false;
  myselfPermission = false;
  saveStatus = false;

  previewImage = '';
  previewVisible = false;
  // 请求头
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  handlePreview = (file: UploadFile) => {
    this.previewImage = file.url || file.thumbUrl;
    this.previewVisible = true;
  }

  removeFile = (file: UploadFile) => {
    if (this.edit === '编辑') {
      this.msg.info('查看状态下不可修改');
    } else {
      if (!this.myselfPermission) {
        this.msg.warning('权限不足');
      } else {
        return true;
      }
    }
    return false;
  }

  ngOnInit(): void {
    this.initItemInfo();
  }


  initItemInfo() {
    this.http.get<{ item: Item, message: any, user: any }>('/user/item/detail/', { itemId: this.itemId })
      .subscribe(
        res => {
          this.itemInfo = res.item;
          this.message = res.message;
          this.catId = res.item.catId;
          this.user = res.user;
          this.params.userId = this.user.openid;
          const images: string[] = res.item.itemImage.split(',');
          for (let i = 0; i < images.length; i++) {
            this.fileList = this.fileList.concat({
              'uid': -i,
              'url': images[i]
            });
          }
          if (res.user.openid === this.tokenService.get().id) {
            this.adminflag = true;
          }
          this.initItemList();
        });
  }

  initItemList() {
    this.http.get<{ rows: any[], total: any }>(`/user/item/list`, this.params)
      .subscribe(
        res => {
          this.itemList = res;
        });
  }

  changeIndex(): void {
    this.http.get('/user/item/list', this.params)
      .subscribe(
        res => {
          this.itemList = res;
        }
      );
  }

  disabledDate = (current: Date): boolean => {
    return differenceInCalendarDays(current, this.today) > 0;
  }

  clickEdit() {
    if (this.adminflag) {
      this.myselfPermission = !this.myselfPermission;
    } else {
      this.adminPermission = !this.adminPermission;
    }
    if (this.edit === '编辑') {
      this.edit = '取消';
      this.saveStatus = true;
    } else {
      this.edit = '编辑';
      this.saveStatus = false;
    }
  }

  saveForm(myForm: any, flag: any) {
    console.log('myForm: ' + JSON.stringify(myForm));
    console.log('flag: ' + flag);
    this.loding = true;
    myForm.itemId = this.itemId;
    let fileList = '';
    for (let i = 0; i < this.fileList.length; i++) {
      fileList = fileList + this.fileList[i].url;
      if (i < this.fileList.length - 1) {
        fileList = fileList + ',';
      }
    }
    console.log(fileList);
    console.log(fileList === this.itemInfo.itemImage);
    if (!flag) {
      this.http.post<{ status: any, msg: any }>('/user/item/update', this.httpOptions, this.itemInfo)
        .subscribe(
          res => {
            if (res.status === 200) {
              this.msg.success('修改成功');
            } else {
              this.msg.error('修改失败');
            }
          }
        );
        this.ref.close();
    } else {
      this.loding = false;
    }
  }

}

class Item {
  buyTime: string;
  catId: string;
  collectNum: number;
  itemCreated: Data;
  itemId: string;
  itemImage?: string;
  itemTitle: string;
  itemUpdate: Data;
  newnessRate: number;
  openid: string;
  originalPrice: number;
  price: number;
  sellAddress: string;
  sellPhone?: string;
  sellPoint: string;
  sellQq?: string;
  sellWechat?: string;
  status: number;
}
