import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { environment } from '@env/environment';
// layout
import { LayoutDefaultComponent } from '../layout/default/default.component';
import { LayoutFullScreenComponent } from '../layout/fullscreen/fullscreen.component';
import { LayoutPassportComponent } from '../layout/passport/passport.component';
// dashboard pages
import { DashboardWorkplaceComponent } from './dashboard/workplace/workplace.component';
// passport pages
import { UserLoginComponent } from './passport/login/login.component';
import { UserRegisterComponent } from './passport/register/register.component';
import { UserRegisterResultComponent } from './passport/register-result/register-result.component';
// single pages
import { CallbackComponent } from './callback/callback.component';
import { UserLockComponent } from './passport/lock/lock.component';
import { Exception403Component } from './exception/403.component';
import { Exception404Component } from './exception/404.component';
import { Exception500Component } from './exception/500.component';
import { DashboardAnalysisComponent } from './dashboard/analysis/analysis.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutDefaultComponent,
    children: [
      { path: '', redirectTo: 'dashboard/workplace', pathMatch: 'full' },
      { path: 'dashboard', redirectTo: 'dashboard/workplace', pathMatch: 'full' },
      { path: 'dashboard/analysis', component: DashboardAnalysisComponent },
      { path: 'dashboard/workplace', component: DashboardWorkplaceComponent },
      // { path: 'dashboard', component: DashboardWorkplaceComponent, data: { title: '仪表盘', titleI18n: 'dashboard' } },
      // 用户管理子模块
      { path: 'sms', loadChildren: './sms/sms.module#SmsModule'},
      // 商品信息
      { path: 'item-manager', loadChildren: './item-manager/item-manager.module#ItemManagerModule'},
      // 内容管理
      { path: 'content', loadChildren: './content/content.module#ContentModule'},
      // 消息管理
      { path: 'message', loadChildren: './message/message.module#MessageModule' },
      // 个人管理
      { path: 'pro', loadChildren: './pro/pro.module#ProModule' },
      // 第三方网站
      { path: 'website', loadChildren: './website/website.module#WebsiteModule' },
    ]
  },
  // 全屏布局
  // {
  //     path: 'fullscreen',
  //     component: LayoutFullScreenComponent,
  //     children: [
  //     ]
  // },
  // passport
  {
    path: 'passport',
    component: LayoutPassportComponent,
    children: [
      { path: 'login', component: UserLoginComponent, data: { title: '登录', titleI18n: 'pro-login' } },
      { path: 'register', component: UserRegisterComponent, data: { title: '注册', titleI18n: 'pro-register' } },
      { path: 'register-result', component: UserRegisterResultComponent, data: { title: '注册结果', titleI18n: 'pro-register-result' } }
    ]
  },
  // 单页不包裹Layout
  { path: 'callback/:type', component: CallbackComponent },
  { path: 'lock', component: UserLockComponent, data: { title: '锁屏', titleI18n: 'lock' } },
  { path: '403', component: Exception403Component },
  { path: '404', component: Exception404Component },
  { path: '500', component: Exception500Component },
  { path: '**', redirectTo: 'dashboard' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: environment.useHash })],
  exports: [RouterModule]
})
export class RouteRoutingModule { }
