import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { CustomCurrencyPipe } from '../pipes/custom-currency.pipe';

@Component({
  selector: 'app-input',
  template: `
    <div style="padding: 10px 10px 10px 0px">
    <label [for]='id' class="mb-1">{{labelText}}</label>
      <div class="input-group">
          <input 
            [id]='id' 
            [name]='name' 
            [placeholder]='placeholder' 
            [disabled]='disabled' 
            [mask]="mask" 
            [type]='type' 
            [maxLength]='maxLength' 
            class="form-control" 
            [(ngModel)]="value" 
            (ngModelChange)="onModelChange($event)"
          >
          <ng-content></ng-content>
      </div> 
    </div>
  `,

})
export class InputComponent implements OnChanges {
  
  @Input() value?: string | number ;
  @Input() labelText: string = '';
  @Input() type: string = '';
  @Input() id: string = '';
  @Input() name: string = '';
  @Input() placeholder: string = '';
  @Input() maxLength: number = 500;
  @Input() disabled: boolean = false;
  @Input() mask = '';

  @Output() valueChange = new EventEmitter<any>();

  constructor(private currencyPipe : CustomCurrencyPipe) { }
  ngOnChanges(changes: SimpleChanges): void {
    if(this.type === 'currency') {
      this.value = this.value != null ?  this.currencyPipe.transform(this.value) : '';
    }
  }

  onModelChange(event: any): void {
    this.valueChange.emit(this.value);
  }




}
