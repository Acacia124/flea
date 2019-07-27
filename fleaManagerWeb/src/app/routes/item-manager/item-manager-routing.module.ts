import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ItemManagerItemListComponent } from './item-list/item-list.component';
import { ItemManagerItemCatComponent } from './item-cat/item-cat.component';
import { ItemManagerItemIssueComponent } from './item-issue/item-issue.component';

const routes: Routes = [

  { path: 'item-list', component: ItemManagerItemListComponent },
  { path: 'item-cat', component: ItemManagerItemCatComponent },
  { path: 'item-issue', component: ItemManagerItemIssueComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ItemManagerRoutingModule { }
