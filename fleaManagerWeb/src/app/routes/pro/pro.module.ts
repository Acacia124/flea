import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { ProRoutingModule } from './pro-routing.module';
import { ProAccountSettingsComponent } from './setting/settings.component';
import { ProAccountSettingsBaseComponent } from './setting/base/base.component';
import { ProAccountSettingsSecurityComponent } from './setting/security/security.component';
import { ProSettingSecurityModelPasswordComponent } from './setting/security/model/password/password.component';
import { ProSettingSecurityModelEmailComponent } from './setting/security/model/email/email.component';

const COMPONENTS = [
  ProAccountSettingsComponent,
  ProAccountSettingsBaseComponent,
  ProAccountSettingsSecurityComponent
];
const COMPONENTS_NOROUNT = [
  ProSettingSecurityModelPasswordComponent,
  ProSettingSecurityModelEmailComponent];

@NgModule({
  imports: [
    SharedModule,
    ProRoutingModule
  ],
  declarations: [
    ...COMPONENTS,
    ...COMPONENTS_NOROUNT
  ],
  entryComponents: COMPONENTS_NOROUNT
})
export class ProModule { }
