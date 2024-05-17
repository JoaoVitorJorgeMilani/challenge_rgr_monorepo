import { LOCALE_ID, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ColComponent } from './col/app-col.component';
import { GridComponent } from './grid/app-grid.component';
import { RowComponent } from './row/app-row.component';
import { DisplayFieldComponent } from './display-field/display-field.component';
import { CurrencyComponent } from './currency/currency.component';
import { InputComponent } from './input/input.component';
import { TableComponent } from './table/table.component';
import { CustomCurrencyPipe } from './pipes/custom-currency.pipe';
import { AlertComponent } from './alert/alert.component';
import { DatepickerComponent } from './datepicker/datepicker.component';
import { ModalComponent } from './modal/modal.component';
import { CustomDatePipe } from './pipes/custom-date.pipe';
import { SpinnerComponent } from './spinner/spinner.component';
import { FormsModule } from '@angular/forms';
import { NgxMaskDirective, NgxMaskPipe, provideNgxMask } from 'ngx-mask';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  declarations: [
    RowComponent,
    ColComponent,
    GridComponent,
    TableComponent,
    InputComponent,
    CurrencyComponent,
    CustomCurrencyPipe,
    DatepickerComponent,
    ModalComponent,
    SpinnerComponent,
    AlertComponent,
    DisplayFieldComponent,
    CustomDatePipe
  ],
  imports: [
    CommonModule,
    FormsModule,
    NgxMaskDirective,
    NgxMaskPipe,
    FontAwesomeModule

  ],
  exports: [
    RowComponent,
    ColComponent,
    GridComponent,
    TableComponent,
    InputComponent,
    CurrencyComponent,
    CustomCurrencyPipe,
    DatepickerComponent,
    ModalComponent,
    SpinnerComponent,
    AlertComponent,
    DisplayFieldComponent,
    CustomDatePipe
  ],
  providers: [
    provideNgxMask(),
    { provide: LOCALE_ID, useValue: 'pt-BR' }
  ]
})
export class SharedModule { }
