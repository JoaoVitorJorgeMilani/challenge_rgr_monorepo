import { NgModule } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { MainComponent } from './main.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { AppRoutingModule } from '../app-routing.module';
import { SchedulerComponent } from './scheduler/scheduler.component';
import { StatementComponent } from './statement/statement.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule } from '@angular/forms';
import { CustomCurrencyPipe } from '../shared/pipes/custom-currency.pipe';
import { HttpClientModule } from '@angular/common/http';



@NgModule({
  declarations: [
    MainComponent,
    SchedulerComponent,
    StatementComponent
  ],
  imports: [
    HttpClientModule,
    CommonModule,
    FormsModule,
    FontAwesomeModule,
    AppRoutingModule,
    SharedModule
  ],
  providers: [
    CustomCurrencyPipe
  ]
})
export class MainModule { }