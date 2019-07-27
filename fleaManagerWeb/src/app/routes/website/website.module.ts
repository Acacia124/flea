import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { WebsiteRoutingModule } from './website-routing.module';
import { WebsiteAliyunComponent } from './aliyun/aliyun.component';
import { SafePipe } from './safe.pipe';
import { WebsiteBaiduComponent } from './baidu/baidu.component';
import { WebsiteWechatComponent } from './wechat/wechat.component';

const COMPONENTS = [
  WebsiteAliyunComponent,
  SafePipe
,
  WebsiteBaiduComponent,
  WebsiteWechatComponent];
const COMPONENTS_NOROUNT = [
];

@NgModule({
  imports: [
    SharedModule,
    WebsiteRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class WebsiteModule { }
