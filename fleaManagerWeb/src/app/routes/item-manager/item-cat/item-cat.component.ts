import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { _HttpClient, ModalHelper } from '@delon/theme';
import { STColumn, STComponent } from '@delon/abc';
import { SFSchema } from '@delon/form';
// tslint:disable-next-line:max-line-length
import { NzTreeNode, NzFormatEmitEvent, NzDropdownService, NzDropdownContextComponent, NzTreeComponent, NzMessageService, NzModalService, NzModalRef } from 'ng-zorro-antd';
import { truncate } from 'fs';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-item-manager-item-cat',
  templateUrl: './item-cat.component.html',
  styles: [`
  .search{
    color: fuchsia
  }
  ` ]
})
export class ItemManagerItemCatComponent implements OnInit {

  constructor(private nzDropdownService: NzDropdownService, private http: _HttpClient, public msg: NzMessageService,
    private modalService: NzModalService) {
  }

  @ViewChild('treeCom') treeCom;
  searchValue;
  searchTree = [];

  dropdown: NzDropdownContextComponent;
  // actived node
  activedNode: NzTreeNode;
  nodes = [];

  loading = true;

  // 操作的Node节点信息
  node: any;
  // 自定义key
  key = 0;

  isVisible = false;
  isOkLoading = false;

  // 初始化分类列表
  initCat() {
    this.http.get<[{}]>('/item/cat/list/all')
      .pipe(
        map((list: any) =>
          list.map(i => {
            i.edit = false;
            return i;
          }),
        ),
    )
      .subscribe(
        res => {
          this.nodes = res;
          this.loading = false;
        }
      );
  }

  ngOnInit(): void {
    this.initCat();
  }

  openFolder(data: NzTreeNode | NzFormatEmitEvent): void {
    // do something if u want
    if (data instanceof NzTreeNode) {
      data.isExpanded = !data.isExpanded;
    } else {
      data.node.isExpanded = !data.node.isExpanded;
    }
  }

  activeNode(data: NzFormatEmitEvent): void {
    if (this.activedNode) {
      // delete selectedNodeList(u can do anything u want)
      this.treeCom.nzTreeService.setSelectedNodeList(this.activedNode);
    }
    data.node.isSelected = true;
    this.activedNode = data.node;
    // add selectedNodeList
    this.treeCom.nzTreeService.setSelectedNodeList(this.activedNode);
  }

  // 右键
  contextMenu($event: MouseEvent, template: TemplateRef<void>, node: any): void {
    this.dropdown = this.nzDropdownService.create($event, template);
    this.node = node;
  }

  // 创建空的Node节点
  createBlankNode(): void {
    this.dropdown.close();
    if (this.node !== null) {
      if (!this.node.isLeaf) {
        if (!this.node.isExpanded) {
          this.node.isExpanded = true;
        }
        this.loadBlankCat(this.node).then(data => {
          this.node.addChildren(data);
        });
      }
    }
  }

  loadBlankCat(id: any): Promise<any[]> {
    const child = [
      {
        title: ' ', key: --this.key, edit: true, isLeaf: true,
      }
    ];
    return new Promise(resolve => {
      setTimeout(() => resolve(child),
        1);
    });
  }

  addNode(node: any, nodeName: any) {
    nodeName = nodeName.replace(/\s+/g, '');
    if (nodeName.length > 0) {
      if (node.key < 0) {
        node.origin.edit = false;
        console.log(nodeName);
        this.http.get<any>('/user/item/cat/add', { parentId: this.node.key, catName: nodeName })
          .subscribe(
            res => {
              if (res.status === 200) {
                node.title = nodeName;
                node.key = res.data.catId;
              } else {
                this.msg.error(res.msg);
                this.cancelNode(node);
              }
            }
          );
      } else {
        if (nodeName !== this.node.title) {
          this.http.get<any>('/user/item/cat/update', { id: node.key, catName: nodeName })
            .subscribe(
              res => {
                if (res.status === 200) {
                  node.title = nodeName;
                } else {
                  this.msg.error(res.msg);
                  this.cancelNode(node);
                }
              }
            );
        }
        node.origin.edit = false;
      }
    } else {
      this.msg.info('输入合法分类名称');
    }
  }

  cancelNode(node: any) {
    if (node.key < 0) {
      this.node.children = this.node.children.filter(x => x.key !== node.key);
    } else {
      this.node.origin.edit = false;
    }
  }

  updateNode() {
    this.dropdown.close();
    this.node.origin.edit = true;
  }


  showModal(): void {
    this.isVisible = true;
  }

  handleOk(): void {
    this.isOkLoading = true;
    this.http.get<any>('/user/item/cat/delete', {catId: this.node.key})
    .subscribe(
      res => {
        if (res.status === 200) {
          this.node.getParentNode().children = this.node.getParentNode().children.filter(x => x.key !== this.node.key);
        } else {
          this.msg.error(res.data);
        }
        this.dropdown.close();
        this.isVisible = false;
        this.isOkLoading = false;
      }
    );
  }

  handleCancel(): void {
    this.isVisible = false;
    this.dropdown.close();
  }

  nzEvent(): void {
    this.searchTree = this.treeCom.getMatchedNodeList().map(v => v.title);
  }
}
