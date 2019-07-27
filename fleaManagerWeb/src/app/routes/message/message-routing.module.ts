import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MessageStationLetterComponent } from './station-letter/station-letter.component';
import { MessageIMComponent } from './im/im.component';
import { UnsaveGuard } from './im/unsave.guard';

const routes: Routes = [

  { path: 'station-letter', component: MessageStationLetterComponent },
  { path: 'IM', component: MessageIMComponent, canDeactivate: [UnsaveGuard] }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
  providers: [UnsaveGuard]
})
export class MessageRoutingModule { }
