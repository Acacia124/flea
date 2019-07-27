import { Component, OnInit, ViewChild, ElementRef, Inject, AfterViewChecked } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent } from '@delon/abc';
import { SFSchema } from '@delon/form';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { TokenService, DA_SERVICE_TOKEN } from '@delon/auth';
import { webSocket } from 'rxjs/webSocket';
import { CacheService } from '@delon/cache';
import { Router, NavigationEnd, NavigationStart } from '@angular/router';
import { BehaviorSubject, Observable } from 'rxjs';
import { map, debounceTime, switchMap } from 'rxjs/operators';

@Component({
    selector: 'app-message-im',
    templateUrl: './im.component.html',
    styleUrls: ['./im.component.css']
})
export class MessageIMComponent implements OnInit, AfterViewChecked {

    searchChange$ = new BehaviorSubject('');
    optionList = [];
    selectedUser: string;
    isLoading = false;

    ws: WebSocket; // 定义websocket
    msgList = [];

    @ViewChild('chatList') private myScrollContainer: ElementRef;
    // 聊天用户列表
    userList = [];
    // 正在聊天用户id
    i = 0;
    inputValue = '';
    // 正在聊天的用户
    chatUser = null;

    isVisible = false;
    item_imgs = [];
    item;
    user;
    selectedIndex = 0;
    constructor(private http: _HttpClient, public msg: NzMessageService,
        @Inject(DA_SERVICE_TOKEN) private tokenService: TokenService,
        public srv: CacheService, private modalService: NzModalService) {
    }

    ngOnInit(): void {
        this.userList = this.srv.get('/user/IM/list', { mode: 'none' }) === null ? [] : this.srv.get('/user/IM/list', { mode: 'none' });
        console.log('His: ', this.userList);
        this.http.get<any>('/user/IM/list')
            .subscribe(
                res => {
                    if (res.status === 200) {
                        if (res.data === null || res.data.length === 0) {
                        } else if (this.userList.length === 0) {
                            this.userList = res.data;
                        } else {
                            // 整合两个元素
                            const userList = [];
                            for (let i = 0; i < this.userList.length; i++) {
                                for (let j = 0; j < res.data.length; j++) {
                                    if (this.userList[i].openId === res.data[j].openId) {
                                        userList.push(res.data[j]);
                                        break;
                                    }
                                    if (j === res.data.length - 1) {
                                        userList.push(this.userList[i]);
                                    }
                                }
                            }
                            for (let j = 0; j < res.data.length; j++) {
                                for (let i = 0; i < this.userList.length; i++) {
                                    if (this.userList[i].openId === res.data[j].openId) {
                                        break;
                                    }
                                    if (i === this.userList.length - 1) {
                                        userList.push(res.data[j]);
                                    }
                                }
                            }
                            this.userList = userList;
                        }
                    } else {
                        this.msg.error(res.msg);
                    }
                }
            );
        this.optionList = this.userList;
    }

    clickUser(user) {
        if (user === this.chatUser) {
            return;
        }
        // 保存缓存
        if (this.chatUser !== null) {
            this.srv.set(this.tokenService.get().id + '/' + this.chatUser.openId, this.msgList);
        }
        this.msgList = [];
        // 取缓存
        const msgList = this.srv.get(this.tokenService.get().id + '/' + user.openId, { mode: 'none' });
        if (msgList != null) {
            this.msgList = msgList;
        }
        this.i = user.openId;
        this.chatUser = user;
        this.connectChat(user);
    }

    connectChat(user) {
        if (this.ws !== undefined && this.ws.readyState === 1) {
            this.ws.close();
        }
        const i = this.userList.indexOf(user);
        this.userList[i].msgNum = '';
        const that = this;
        const url = 'ws://101.200.56.187:8081/socketServer/im/' + this.tokenService.get().id + '/' + user.openId;
        this.ws = new WebSocket(url);
        this.ws.onopen = function () {
        };
        this.ws.onmessage = function (evt) {
            const msgList = JSON.parse(evt.data);
            if (msgList.status !== undefined) {
                for (let index = 0; index < msgList.data.length; index++) {
                    that.msgList.push(msgList.data[index]);
                }
            } else {
                that.msgList.push(msgList);
            }
            that.myScrollContainer.nativeElement.scrollTop = that.myScrollContainer.nativeElement.scrollHeight;
        };
    }

    sendMessage() {
        if (this.inputValue === '') {
            return;
        }
        this.ws.send(JSON.stringify(
            {
                msgType: '1',
                content: this.inputValue
            }
        ));
        this.msgList.push({
            msgType: 1,
            sendTo: this.chatUser.openId,
            createBy: this.tokenService.get().id,
            createTime: new Date(),
            delFlag: 1,
            isOffLine: 1,
            content: this.inputValue
        });
        // 更新userList数据
        const i = this.userList.indexOf(this.chatUser);
        this.userList[i].lastMsg = this.inputValue;
        this.userList[i].lastDate = new Date();
        this.inputValue = '';
    }

    // 保存信息（供路由守卫使用）
    saveInfo() {
        if (this.chatUser !== null) {
            this.srv.set(this.tokenService.get().id + '/' + this.chatUser.openId, this.msgList);
        }
        this.srv.set('/user/IM/list', this.userList);
        if (this.ws !== undefined && this.ws.readyState === 1) {
            this.ws.close();
        }
    }

    // 搜索用户
    onSearch(value: string): void {
        this.isLoading = true;
        if (value.trim() === '') {
            this.optionList = this.userList;
            this.isLoading = false;
        } else {
            if (this.searchChange$.observers.length === 0) {
                this.searchUser();
            }
            this.searchChange$.next(value);
        }
    }

    searchUser() {
        const getRandomNameList = (name: string) =>
            this.http
                .get('/user/getUserLike/' + name)
                .pipe(map((res: any) => res.data))
                .pipe(
                    map((list: any) => {
                        return list.map((item: any) => item);
                    })
                );
        const optionList$: Observable<any> = this.searchChange$
            .asObservable()
            .pipe(debounceTime(500))
            .pipe(switchMap(getRandomNameList));
        optionList$.subscribe(data => {
            this.optionList = data;
            this.isLoading = false;
        });
    }

    changeUser(value: any) {
        console.log(value);
        // 将值加入userList，并定位
        if (value !== null) {
            if (this.userList.filter(res => res.openId === value.openId) === null ||
                this.userList.filter(res => res.openId === value.openId).length === 0) {
                this.userList.splice(0, 0, value);
                this.clickUser(value);
            } else {
                this.clickUser(value);
            }
        }
    }

    showItemDetail(id) {
        this.http.get<any>('/user/item/detail/', { itemId: id })
            .subscribe(
                res => {
                    this.item = res.item;
                    this.item_imgs = res.item.itemImage.split(',');
                    this.user = res.user;

                    this.isVisible = true;
                });
    }

    handleCancel() {
        this.item = null;
        this.item_imgs = [];
        this.user = null;
        this.isVisible = false;
    }

    changeSelectedIndex(tab) {
        this.selectedIndex = tab;
    }

    formatter(value) {
        return `成新率：${value}%`;
    }

    uploadFile($event) {
        if ($event.type === 'success') {
            if($event.file.response.status !== 200) {
                this.msg.error('图片发送失败！');
                return;
            }
            this.ws.send(JSON.stringify(
                {
                    msgType: '2',
                    content: $event.file.response.data
                }
            ));
            this.msgList.push({
                msgType: 2,
                sendTo: this.chatUser.openId,
                createBy: this.tokenService.get().id,
                createTime: new Date(),
                delFlag: 1,
                isOffLine: 1,
                content: $event.file.response.data
            });
            // 更新userList数据
            const i = this.userList.indexOf(this.chatUser);
            this.userList[i].lastMsg = '[图片]';
            this.userList[i].lastDate = new Date();
        }
    }

    ngAfterViewChecked() {
        this.myScrollContainer.nativeElement.scrollTop = this.myScrollContainer.nativeElement.scrollHeight;
    }
}
