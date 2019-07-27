import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { WebsiteAliyunComponent } from './aliyun/aliyun.component';
import { WebsiteBaiduComponent } from './baidu/baidu.component';
import { WebsiteWechatComponent } from './wechat/wechat.component';

const routes: Routes = [

  { path: 'aliyun', component: WebsiteAliyunComponent },
  { path: 'baidu', component: WebsiteBaiduComponent },
  { path: 'wechat', component: WebsiteWechatComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class WebsiteRoutingModule { }
