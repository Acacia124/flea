import { Injectable, Injector, Inject } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { zip } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MenuService, SettingsService, TitleService, ALAIN_I18N_TOKEN } from '@delon/theme';
import { DA_SERVICE_TOKEN, ITokenService } from '@delon/auth';
import { ACLService } from '@delon/acl';

import { NzIconService } from 'ng-zorro-antd';
import { ICONS_AUTO } from '../../../style-icons-auto';
import { ICONS } from '../../../style-icons';

/**
 * 用于应用启动时
 * 一般用来获取应用所需要的基础数据等
 */
@Injectable()
export class StartupService {
  constructor(
    iconSrv: NzIconService,
    private menuService: MenuService,
    private settingService: SettingsService,
    private aclService: ACLService,
    private titleService: TitleService,
    @Inject(DA_SERVICE_TOKEN) private tokenService: ITokenService,
    private httpClient: HttpClient,
    private injector: Injector
  ) {
    iconSrv.addIcon(...ICONS_AUTO, ...ICONS);
  }

  private viaHttp(resolve: any, reject: any) {
    const tokenData = this.tokenService.get();
    if (!tokenData.token) {
      this.injector.get(Router).navigateByUrl('/passport/login');
      resolve({});
      return;
    }
    const app: any = {
      name: `物不如故中后台管理`,
      description: `物不如故二手交易市场中后台管理面板`
    };
    const user: any = {
      name: tokenData.name,
      avatar: tokenData.avatarUrl,
      email: tokenData.email,
      token: tokenData.token
    };
    // 应用信息：包括站点名、描述、年份
    this.settingService.setApp(app);
    // 用户信息：包括姓名、头像、邮箱地址
    this.settingService.setUser(user);
    // ACL：设置权限为全量
    this.aclService.setFull(true);
    // 初始化菜单
    this.menuService.add([
      {
        text: '主导航',
        group: true,
        children: [
          {
            text: '仪表盘',
            icon: { type: 'icon', value: 'appstore' },
            shortcutRoot: true,
            children: [
              {
                text: '工作台',
                link: '/dashboard/workplace',
                shortcutRoot: false,
              },
              {
                text: '分析页',
                link: '/dashboard/analysis',
                shortcutRoot: false,
              }
            ]
          }
        ]
      },
      {
        text: '商城管理',
        group: true,
        children: [
          {
            text: '用户管理',
            icon: { type: 'icon', value: 'team' },
            shortcutRoot: true,
            children: [
              {
                text: '用户列表',
                link: '/sms/user-list',
                shortcutRoot: false,
              },
              {
                text: '地址列表',
                link: '/sms/school-tree',
                shortcutRoot: false,
              }
            ]
          },
          {
            text: '内容管理',
            icon: { type: 'icon', value: 'setting' },
            shortcutRoot: true,
            children: [
              {
                text: '内容列表',
                link: '/content/list',
                shortcutRoot: false,
              }
            ]
          },
          {
            text: '商品管理',
            icon: { type: 'icon', value: 'global' },
            shortcutRoot: true,
            children: [
              {
                text: '新增商品',
                link: '/item-manager/item-issue',
                shortcutRoot: false,
              },
              {
                text: '商品列表',
                link: '/item-manager/item-list',
                shortcutRoot: false,
              },
              {
                text: '商品分类',
                link: '/item-manager/item-cat',
                shortcutRoot: false,
              },
            ]
          },
          {
            text: '消息管理',
            icon: { type: 'icon', value: 'message' },
            shortcutRoot: true,
            children: [
              {
                text: '站内信',
                link: '/message/station-letter',
                shortcutRoot: false,
              },
              {
                text: '即时通讯',
                link: '/message/IM',
                shortcutRoot: false,
              }
            ]
          },
          {
            text: '个人中心',
            icon: { type: 'icon', value: 'user' },
            shortcutRoot: true,
            children: [
              {
                text: '个人设置',
                link: '/pro/settings',
                shortcutRoot: false,
              }
            ]
          },
          {
            text: '三方服务',
            icon: { type: 'icon', value: 'usb' },
            shortcutRoot: true,
            children: [
              {
                text: '百度人脸识别',
                link: '/website/baidu',
                shortcutRoot: false,
              },
              {
                text: '阿里云服务',
                link: '/website/aliyun',
                shortcutRoot: false,
              },
              {
                text: '微信小程序',
                link: '/website/wechat',
                shortcutRoot: false,
              }
            ]
          },
        ]
      },
    ]);
    // 设置页面标题的后缀
    this.titleService.suffix = app.name;

    resolve({});
    // zip(
    //   this.httpClient.get('assets/tmp/app-data.json')
    // ).pipe(
    //   // 接收其他拦截器后产生的异常消息
    //   catchError(([appData]) => {
    //       resolve(null);
    //       return [appData];
    //   })
    // ).subscribe(([appData]) => {

    //   // application data
    //   const res: any = appData;
    //   // 应用信息：包括站点名、描述、年份
    //   this.settingService.setApp(res.app);
    //   // 用户信息：包括姓名、头像、邮箱地址
    //   this.settingService.setUser(res.user);
    //   // ACL：设置权限为全量
    //   this.aclService.setFull(true);
    //   // 初始化菜单
    //   this.menuService.add(res.menu);
    //   // 设置页面标题的后缀
    //   this.titleService.suffix = res.app.name;
    // },
    // () => { },
    // () => {
    //   resolve(null);
    // });
  }

  load(): Promise<any> {
    return new Promise((resolve, reject) => {
      this.viaHttp(resolve, reject);
    });
  }
}
