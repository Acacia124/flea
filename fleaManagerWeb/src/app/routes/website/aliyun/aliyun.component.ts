import { Component, OnInit, ViewChild } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { DomSanitizer } from '@angular/platform-browser';
import { fromEvent } from 'rxjs/index';
declare var document: Document;

@Component({
  selector: 'app-website-aliyun',
  templateUrl: './aliyun.component.html',
})
export class WebsiteAliyunComponent implements OnInit {
  public orbitUrl: string;
  outHeight = '0px';
  fullscreen = false;
  constructor(private sanitizer: DomSanitizer) {
    this.orbitUrl = 'https://account.aliyun.com/';
    this.outHeight = window.innerHeight + 'px';
    fromEvent(window, 'resize').subscribe(($event) => {
      this.outHeight = window.innerHeight + 'px';
    });
  }
  clickFull() {
    this.fullscreen = !this.fullscreen;
    let iframe;
    iframe = document.getElementById('orbitIframe');
    if (this.fullscreen) {
      iframe.scrolling = 'auto';
    } else {
      iframe.scrolling = 'no';
    }
  }

  ngOnInit() { }
}
