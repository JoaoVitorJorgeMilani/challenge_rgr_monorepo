import { formatDate } from '@angular/common';
import { Component, ElementRef, Input, ViewChild, forwardRef } from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => DatepickerComponent),
      multi: true
    }
  ],  
  selector: 'app-datepicker',
  template: `
    <div style="padding: 10px 10px 10px 0px">
      <label [for]='id' class="mb-1">{{labelText}}</label>
      <input #datepickerField id="startDate" class="form-control" type="date" (input)="onInput($event)" [min]='minx' />
    </div>
  `
})
export class DatepickerComponent {

  @ViewChild('datepickerField') datepickerField!: ElementRef;
  
  @Input() labelText: string = '';
  @Input() id: string = '';
  @Input() name: string = '';
  @Input() disabled: boolean = false;
  @Input() minDate: string = '1970-01-01';
  @Input() startsToday: boolean = false;

  private onChange!: (value: any) => void;

  onInput(event: any): void {
    this.onChange(event.target.value);
  }

  writeValue(value: any): void {
    this.datepickerField.nativeElement.value = value;
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
  }

  setDisabledState(isDisabled: boolean): void {
    this.datepickerField.nativeElement.disabled = isDisabled;
  }

  get minx () : string {
    return this.startsToday ? formatDate(new Date(), 'yyyy-MM-dd', 'pt-BR') : formatDate(this.minDate, 'yyyy-MM-dd', 'pt-BR')
  }



}
