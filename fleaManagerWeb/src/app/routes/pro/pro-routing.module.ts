import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ProAccountSettingsBaseComponent } from './setting/base/base.component';
import { ProAccountSettingsComponent } from './setting/settings.component';
import { ProAccountSettingsSecurityComponent } from './setting/security/security.component';
const routes: Routes = [
  {
    path: 'settings',
    component: ProAccountSettingsComponent,
    children: [
      { path: '', redirectTo: 'base', pathMatch: 'full' },
      {
        path: 'base',
        component: ProAccountSettingsBaseComponent,
        data: { titleI18n: 'pro-account-settings' },
      },
      {
        path: 'security',
        component: ProAccountSettingsSecurityComponent,
        data: { titleI18n: 'pro-account-settings' },
      }
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProRoutingModule { }
