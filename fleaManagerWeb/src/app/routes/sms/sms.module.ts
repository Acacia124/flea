import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { SmsRoutingModule } from './sms-routing.module';
import { SmsUserListComponent } from './user-list/user-list.component';
import { SmsUserListViewComponent } from './user-list/view/view.component';
import { SmsSchoolTreeComponent } from './school-tree/school-tree.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { SimpleInterceptor } from '@delon/auth';

const COMPONENTS = [
  SmsUserListComponent,
  SmsSchoolTreeComponent];
const COMPONENTS_NOROUNT = [
  SmsUserListViewComponent];

@NgModule({
  imports: [
    SharedModule,
    SmsRoutingModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: SimpleInterceptor, multi: true }
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class SmsModule { }
