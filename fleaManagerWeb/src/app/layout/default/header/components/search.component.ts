import { Component, HostBinding, Input, ElementRef, AfterViewInit } from '@angular/core';

@Component({
  selector: 'header-search',
  template: `
  <nz-input-group nzAddOnBeforeIcon="anticon anticon-search">
    <input nz-input [(ngModel)]="q" (focus)="qFocus()" (blur)="qBlur()"
      [placeholder]="'搜索：用户（根据OpenId或用户名定位用户）'">
  </nz-input-group>
  `
})
export class HeaderSearchComponent implements AfterViewInit {

  q: string;

  qIpt: HTMLInputElement;

  @HostBinding('class.alain-default__search-focus')
  focus = false;

  @HostBinding('class.alain-default__search-toggled')
  searchToggled = false;

  @Input()
  set toggleChange(value: boolean) {
    if (typeof value === 'undefined') return;
    this.searchToggled = true;
    this.focus = true;
    setTimeout(() => this.qIpt.focus(), 300);
  }

  constructor(private el: ElementRef) {}

  ngAfterViewInit() {
    this.qIpt = (this.el.nativeElement as HTMLElement).querySelector('.ant-input') as HTMLInputElement;
  }

  qFocus() {
    this.focus = true;
  }

  qBlur() {
    this.focus = false;
    this.searchToggled = false;
  }
}
