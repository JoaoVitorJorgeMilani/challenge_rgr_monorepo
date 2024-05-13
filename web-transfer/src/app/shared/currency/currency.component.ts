import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-currency',
  template: `
    <div style="padding: 10px 10px 10px 0px">
      <label [for]='id' class="mb-1">{{labelText}}</label>
        <input 
          [(ngModel)]="value"
          [id]='id' 
          [name]='name' 
          [placeholder]='placeholder' 
          [disabled]='disabled' 
          mask="separator.2"
          class="form-control" 
          [thousandSeparator]="'.'"
          [decimalMarker]="','"
          prefix="R$ "
          type="text"
          (ngModelChange)="onModelChange($event)"
        >
    </div>
  `
})
export class CurrencyComponent {
  // @ViewChild('inputField') inputField!: ElementRef;
  @Input() value!: number;
  @Input() labelText: string = '';
  @Input() id: string = '';
  @Input() name: string = '';
  @Input() placeholder: string = '';
  @Input() disabled: boolean = false;

  @Output() valueChange = new EventEmitter<any>();

  onModelChange(event: any): void {
    this.valueChange.emit(this.value);
  }
}