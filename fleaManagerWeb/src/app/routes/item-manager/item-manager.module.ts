import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { ItemManagerRoutingModule } from './item-manager-routing.module';
import { ItemManagerItemListComponent } from './item-list/item-list.component';
import { ItemManagerItemCatComponent } from './item-cat/item-cat.component';
import { ItemManagerItemListItemInfoComponent } from './item-list/item-info/item-info.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { SimpleInterceptor } from '@delon/auth';
import { ItemManagerItemIssueComponent } from './item-issue/item-issue.component';

const COMPONENTS = [
  ItemManagerItemListComponent,
  ItemManagerItemCatComponent,
  ItemManagerItemIssueComponent];
const COMPONENTS_NOROUNT = [
  ItemManagerItemListItemInfoComponent];

@NgModule({
  imports: [
    SharedModule,
    ItemManagerRoutingModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: SimpleInterceptor, multi: true }],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT,
    ItemManagerItemListItemInfoComponent
  ],
  entryComponents: [
    COMPONENTS_NOROUNT,
    ItemManagerItemListItemInfoComponent
  ]
})
export class ItemManagerModule { }
