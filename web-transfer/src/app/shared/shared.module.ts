import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ColComponent } from './col/app-col.component';
import { GridComponent } from './grid/app-grid.component';
import { RowComponent } from './row/app-row.component';



@NgModule({
  declarations: [
    GridComponent,
    RowComponent,
    ColComponent
  ],
  imports: [
    CommonModule
  ],
  exports: [
    GridComponent,
    RowComponent,
    ColComponent
  ]
})
export class SharedModule { }
