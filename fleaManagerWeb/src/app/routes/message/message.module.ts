import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { MessageRoutingModule } from './message-routing.module';
import { MessageStationLetterComponent } from './station-letter/station-letter.component';
import { MessageIMComponent } from './im/im.component';

const COMPONENTS = [
  MessageStationLetterComponent,
  MessageIMComponent];
const COMPONENTS_NOROUNT = [];

@NgModule({
  imports: [
    SharedModule,
    MessageRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class MessageModule { }
