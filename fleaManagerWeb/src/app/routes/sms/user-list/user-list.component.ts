import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { NzMessageService } from 'ng-zorro-antd';
import { map, delay } from 'rxjs/operators';
import { of } from 'rxjs';
import { SmsUserListViewComponent } from './view/view.component';

@Component({
  selector: 'app-sms-user-list',
  templateUrl: './user-list.component.html',
  styles: [
    `.full {
      overflow-y: auto;
    }
    .full::-webkit-scrollbar {
        /*滚动条整体样式*/
        width: 4px;
        /*高宽分别对应横竖滚动条的尺寸*/
        height: 4px;
    }

    .full::-webkit-scrollbar-thumb {
        /*滚动条里面小方块*/
        border-radius: 5px;
        -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
        background: rgba(0, 0, 0, 0.2);
    }

    .full::-webkit-scrollbar-track {
        /*滚动条里面轨道*/
        -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
        border-radius: 0;
        background: rgba(0, 0, 0, 0.1);
    }
    `
  ]
})
export class SmsUserListComponent implements OnInit {
  size = 'default';
  schoolList: any;
  url = `/user/list`;
  params: any = {
    'nickName': '',
    'schoolList': [],
  };
  scroll = { y: '230px' };

  @ViewChild('st') comp: STComponent;

  columns: STColumn[] = [
    { title: 'id', index: 'openid', type: 'checkbox' },
    { title: '头像', index: 'avatarUrl', type: 'img', width: '80px' },
    {
      title: '昵称',
      index: 'nickName',
      width: '150px',
    },
    { title: '学校', index: 'userSchool', width: '120px' },
    { title: '积分', type: 'number', index: 'score', width: '100px' },
    {
      title: '性别',
      index: 'gender',
      type: 'yn',
      ynTruth: 2,
      ynYes: '女',
      ynNo: '男',
      width: '60px',
    },
    { title: '注册时间', index: 'userCreated', type: 'date', width: '150px' },
    {
      title: '状态',
      index: 'userStatus',
      type: 'badge',
      badge: {
        1: { text: '普通会员', color: 'processing' },
        2: { text: '已被封号', color: 'default' },
        3: { text: '管理员', color: 'success' }
      },
      filter: {
        menus: [
          { text: '普通会员', value: 1 },
          { text: '已被封号', value: 2 },
          { text: '管理员', value: 3 },
        ],
      },
      width: '100px'
    },
    {
      title: '操作',
      width: '170px',
      buttons: [
        {
          text: '查看',
          type: 'drawer',
          drawer: {
            component: SmsUserListViewComponent
          },
        },
        {
          text: '权限升级',
          pop: true,
          popTitle: ' 已被封号->普通用户 || 普通用户->管理员 ',
          click: (user: any) => {
            if (user.userStatus === 2) {
              this.upgradeUser(user);
            } else if (user.userStatus === 1) {
              this.upgradeUser(user);
            } else {
              this.message.info(`用户[${user.nickName}]权限已最高，勿重复点击`);
            }
          },
        },
        {
          text: '拉黑',
          type: 'del',
          click: (user: any) => {
            if (user.userStatus === 2) {
              this.message.info(`用户[${user.nickName}]已被拉黑，请勿重复提交`);
            } else {
              this.deleteUser(user);
            }
          }
        },
      ],
    },
  ];

  constructor(public http: _HttpClient, private message: NzMessageService) { }

  ngOnInit(): void {
    this.initSchoolList();
  }

  initSchoolList() {
    this.http.get('/school/list').subscribe(
      res => {
        this.schoolList = res;
      }
    );
  }

  deleteUser(user: any) {
    this.http.get<{ status: any, msg: any }>('/user/ban', { ids: user.openid }).subscribe(
      res => {
        if (res.status === 200) {
          this.message.success(`成功删除该用户`);
          user.userStatus = 2;
          this.comp.reload(this.params);
        } else {
          this.message.error(`删除该用户失败[${res.msg}]`);
        }
      }
    );
  }

  upgradeUser(user: any) {
    let s;
    let status;
    if (user.userStatus === 2) {
      status = 1;
      s = '普通用户';
    } else if (user.userStatus === 1) {
      status = 3;
      s = '管理员';
    }
    this.http
      .get<{ status: any, msg: any }>('/user/upgrade', { ids: user.openid, status: status })
      .subscribe(
        res => {
          if (res.status === 200) {
            this.message.success(`用户级别已修改为[${s}]`);
            user.userStatus = status;
            this.comp.reload(this.params);
          } else {
            this.message.error(`修改用户级别失败`);
          }
        }
      );
  }

  fullChange(val: boolean) {
    this.scroll = val ? { y: '350px' } : { y: '230px' };
  }
}
