import { Component, EventEmitter, Input, Output } from '@angular/core';
import { faEye, faEdit, faTrash, IconDefinition, faQuestionCircle } from '@fortawesome/free-solid-svg-icons';
import { PaginationConfig } from './pagination-config-model';

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  styleUrls: ['./table.component.css']
})
export class TableComponent {
    
  @Input() title: string = '';
  @Input() headers: string[] = [];
  @Input() property: string[] = [];
  @Input() data: any[] = [];
  
  @Input() customTableClasses: string = '';
  @Input() customActionIcon: IconDefinition = faQuestionCircle;
  @Input() customActionClass: string = 'btn btn-primary btn-sm';
  @Input() customActionText: string = '';
  @Input() enableActionView: boolean = false;
  @Input() enableActionEdit: boolean = false;
  @Input() enableActionDelete: boolean = false;
  @Input() enableCustomAction: boolean = false;
  @Input() enableAlertInLine: boolean = false;
  @Input() alertConditionProperty: string = '';
  @Input() alertMessage: string = '';

  @Input() showPagination: boolean = false;
  @Input() paginationConfig: PaginationConfig = { itemsPerPage : 0, currentPage : 0, totalItems : 0 };
  
  faEye = faEye;
  faEdit = faEdit;
  faTrash = faTrash;  
  
  @Output() deleteItem = new EventEmitter<any>();
  @Output() editItem = new EventEmitter<any>();
  @Output() viewItem = new EventEmitter<any>();
  @Output() customAction = new EventEmitter<any>();
  @Output() pageChange = new EventEmitter<any>();

  constructor() { }

  onPageChange(event: any){
    this.paginationConfig.currentPage = event;
    this.pageChange.emit(event);
  }

  get enableActions() : boolean {
    return this.enableActionView || this.enableActionDelete || this.enableActionEdit || this.enableCustomAction;
  }

  get hasTitle() : boolean { return this.title.length > 0; }

  hasPipe(prop: string){
    return prop.includes('|');
  }

  isCurrencyPipe(prop: string): boolean {
    return prop.includes('customCurrency');
  }

  isDatePipe(prop: string): boolean {
    return prop.includes('customDate');
  }

  isPercentPipe(prop: string): boolean {
    return prop.includes('percent');
  }

  hasPipeParameter(prop : string): boolean {
    return prop.includes(':');
  }

  getPipeParameter(prop: string): string {
    return prop.split(':')[1].trim();
  }

  getProperty(prop: string): string {
    return prop.split('|')[0].trim();
  }

  onDelete(item: any) {
    this.deleteItem.emit(item);
  }

  onEdit(item: any) {
    this.editItem.emit(item);
  }

  onView(item: any) {
    this.viewItem.emit(item);
  }

  onCustom(item: any) {
    this.customAction.emit(item);
  }

  showDataDanger(item: any): boolean {
    return this.enableAlertInLine && item[this.alertConditionProperty];
  }

  getAlertMessage(item: any): string {
    return item.alertMessage ? item.alertMessage : this.alertMessage ? this.alertMessage : '';
  }
}
