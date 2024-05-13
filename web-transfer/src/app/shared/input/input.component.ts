import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-input',
  template: `
    <div style="padding: 10px 10px 10px 0px">
      <label [for]='id' class="mb-1">{{labelText}}</label>
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
    </div>
  `,

})
export class InputComponent {
  @Input() value!: string | number | Date;
  @Input() labelText: string = '';
  @Input() type: string = '';
  @Input() id: string = '';
  @Input() name: string = '';
  @Input() placeholder: string = '';
  @Input() maxLength: number = 500;
  @Input() disabled: boolean = false;
  @Input() mask = '';

  @Output() valueChange = new EventEmitter<any>();

  onModelChange(event: any): void {
    this.valueChange.emit(this.value);
  }
}
