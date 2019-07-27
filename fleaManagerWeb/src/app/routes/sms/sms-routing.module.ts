import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SmsUserListComponent } from './user-list/user-list.component';
import { SmsSchoolTreeComponent } from './school-tree/school-tree.component';

const routes: Routes = [

  { path: 'user-list', component: SmsUserListComponent },
  { path: 'school-tree', component: SmsSchoolTreeComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SmsRoutingModule { }
