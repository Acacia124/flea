import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

/**
 * safe管道
 * 将url转换为angular 中 iframe可以识别的安全url链接
 */
@Pipe({
    name: 'safe'
})
export class SafePipe implements PipeTransform {
    constructor(private sanitizer: DomSanitizer) { }

    // Angular 2 中提供的 DomSanitizer 服务，可以让url变得安全
    transform(url): any {
        return this.sanitizer.bypassSecurityTrustResourceUrl(url);
    }
}