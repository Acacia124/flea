import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { ContentRoutingModule } from './content-routing.module';
import { ContentListComponent } from './list/list.component';
import { ContentListViewComponent } from './list/view/view.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { SimpleInterceptor } from '@delon/auth';

const COMPONENTS = [
  ContentListComponent];
const COMPONENTS_NOROUNT = [
  ContentListViewComponent];

@NgModule({
  imports: [
    SharedModule,
    ContentRoutingModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: SimpleInterceptor, multi: true }],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class ContentModule { }
