import { Component, Input, OnInit } from '@angular/core';
import { NzDrawerRef } from 'ng-zorro-antd';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-content-list-view',
  templateUrl: './view.component.html',
})
export class ContentListViewComponent implements OnInit {
  @Input()
  record: any;

  imgList = [];

  constructor(private ref: NzDrawerRef, private fb: FormBuilder) { }

  ngOnInit(): void {
    if (this.record.contentPic != null) {
      this.imgList = this.record.contentPic.split(',');
    }
  }

  ok() {
    this.ref.close(`new time: ${+new Date()}`);
    this.cancel();
  }

  cancel() {
    this.ref.close();
  }

}
