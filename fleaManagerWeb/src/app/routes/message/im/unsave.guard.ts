import { CanDeactivate } from '@angular/router';
import { MessageIMComponent } from './im.component';

export class UnsaveGuard implements CanDeactivate<MessageIMComponent> {
    canDeactivate(component: MessageIMComponent) {
        // 用户离开时保存信息列表和用户列表
        component.saveInfo();
        return true;
    }
}
