import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { NzMessageService, NzFormatEmitEvent, NzTreeNodeOptions, NzModalService } from 'ng-zorro-antd';
import { and } from '@angular/router/src/utils/collection';
import { map } from 'rxjs/operators';
import { ItemManagerItemListItemInfoComponent } from './item-info/item-info.component';

@Component({
  selector: 'app-item-manager-item-list',
  templateUrl: './item-list.component.html',
})
export class ItemManagerItemListComponent implements OnInit {
  // endregion

  constructor(private http: _HttpClient, public msg: NzMessageService, private modalService: NzModalService) { }

  endPage = 1;

  params: any = {
    page: 1,
    rows: 8,
    itemName: '',
    schoolList: [],
    userId: '',
    catId: [],
    startPrice: '',
    endPrice: '',
    status: [],
    Date: []
  };

  schoolList: any = [
    {
      schoolId: 0,
      schoolName: '全部',
      value: false
    }
  ];

  catLists = [
  ];

  q: any = {
    ps: 8,
    categories: [],
    owners: ['zxx'],
  };


  list: any[] = [];

  loading = true;

  loadingMore = false;


  // 初始化地址列表
  initSchoolList(): void {
    this.http
      .get('/school/list')
      .pipe(
        map((list: any) =>
          list.map(i => {
            i.value = false;
            return i;
          }),
        ),
    )
      .subscribe(res => {
        this.schoolList = this.schoolList.concat(res);
      });
  }


  // 初始化分类列表
  initCat() {
    this.http.get<[{}]>('/item/cat/list/all')
      .subscribe(
        res => {
          this.catLists = res;
        }
      );
  }


  // 动态获取用户点击地址列表事件
  changeSchoolList(status: boolean, idx: number) {
    const schoolList = [];
    if (idx === 0) {
      this.schoolList.map(i => (i.value = status));
    } else {
      this.schoolList[idx].value = status;
    }

    this.params.schoolList = this.schoolList.filter(x => x.value === true && x.schoolId !== 0);
    for (let i = 0; i < this.params.schoolList.length; i++) {
      schoolList.push(this.params.schoolList[i].schoolName);
    }
    this.params.schoolList = schoolList;
  }

  ngOnInit() {
    this.initSchoolList();
    this.getData();
    this.initCat();
  }


  // 搜索
  searchItemList() {
    this.loading = true;
    this.params.page = 1;
    this.http.get<{ rows, total }>('/user/item/list', this.params)
      .subscribe(
        res => {
          this.list = res.rows;
          this.endPage = res.total / 8;
          console.log(this.params.page + ' ' + this.endPage);
          if (this.params.page >= this.endPage && this.endPage > (this.params.page - 1)) {
            this.loadingMore = true;
          } else {
            this.loadingMore = false;
          }
          this.loading = false;
        }
      );
  }

  // 检查价格范围合法性（bug 当起始价格为0时，结束价格自动为false）
  checkEndPrice() {
    if (this.params.endPrice = null || this.params.endPrice < this.params.startPrice) {
      this.params.endPrice = this.params.startPrice;
    }
  }

  // 初始化商品列表
  getData() {
    this.loading = true;
    this.loadingMore = true;
    this.http.get<{ rows, total }>('/user/item/list', this.params)
      .subscribe(
        res => {
          this.list = res.rows;
          this.endPage = res.total / 8;
          if (this.params.page >= this.endPage) {
            this.loadingMore = true;
          } else {
            this.loadingMore = false;
          }
          this.loading = false;
        }
      );
  }

  // 加载更多
  onLoadMore(): void {
    this.loading = true;
    this.params.page = this.params.page + 1;
    this.http.get<{ rows, total }>('/user/item/list', this.params)
      .subscribe(
        res => {
          this.list = this.list.concat(res.rows);
          this.endPage = res.total / 8;
          if (this.params.page >= this.endPage) {
            this.loadingMore = true;
          } else{
            this.loadingMore = false;
          }
          this.loading = false;
        }
      );
  }

  // 唤起查看/编辑组件
  clickLookOrEditItem(itemId: any) {
    const modal = this.modalService.create({
      nzTitle: '商品详情/编辑页面',
      nzContent: ItemManagerItemListItemInfoComponent,
      nzWidth: 800,
      nzFooter: null,
      nzComponentParams: {
        itemId: itemId,
        catLists: this.catLists
      },
      // nzFooter: [{
      //   label: 'change component tilte from outside',
      //   onClick: (componentInstance) => {
      //     componentInstance.title = 'title in inner component is changed';
      //   }
      // }]
    });

    // modal.afterOpen.subscribe(() => console.log('[afterOpen] emitted!'));

    // // Return a result when closed
    // modal.afterClose.subscribe((result) => console.log('[afterClose] The result is:', result));

    // // delay until modal instance created
    // window.setTimeout(() => {
    //   const instance = modal.getContentComponent();
    //   instance.subtitle = 'sub title is changed';
    // }, 2000);
  }
}
