import { formatDate } from '@angular/common';
import { Component, ElementRef, EventEmitter, Input, Output, ViewChild, forwardRef } from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-datepicker',
  template: `
    <div style="padding: 10px 10px 10px 0px">
      <label [for]='id' class="mb-1">{{labelText}}</label>
      <input 
        id="startDate" 
        class="form-control" 
        type="date" 
        [min]='minValue' 
        [max]='maxValue' 
        [(ngModel)]="value"
        (ngModelChange)="onModelChange($event)" 
      />
    </div>
  `
})
export class DatepickerComponent {

  @ViewChild('datepickerField') datepickerField!: ElementRef;
  
  @Input() value: string = '';

  @Input() labelText: string = '';
  @Input() id: string = '';
  @Input() name: string = '';
  @Input() disabled: boolean = false;
  @Input() min: string = '';
  @Input() max: string = '';
  @Input() startsToday: boolean = false;
  @Input() endsToday: boolean = false;

  @Output() valueChange = new EventEmitter<any>();

  onModelChange(event: any): void {
    console.log("MODEL CHANGED");
    console.log(event);
    this.valueChange.emit(event);
  }

  writeValue(value: any): void {
    this.datepickerField.nativeElement.value = value;
  }

  setDisabledState(isDisabled: boolean): void {
    this.datepickerField.nativeElement.disabled = isDisabled;
  }

  get minValue () : string {
    return this.startsToday ? formatDate(new Date(), 'yyyy-MM-dd', 'pt-BR') : this.min ?  formatDate(this.min, 'yyyy-MM-dd', 'pt-BR') : '';
  }

  get maxValue () : string {
    return this.endsToday ? formatDate(new Date(), 'yyyy-MM-dd', 'pt-BR') : this.max ?  formatDate(this.max, 'yyyy-MM-dd', 'pt-BR') : '';
  }



}
