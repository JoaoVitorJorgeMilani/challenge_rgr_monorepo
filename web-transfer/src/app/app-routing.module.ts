import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { SchedulerComponent } from './main/scheduler/scheduler.component';
import { StatementComponent } from './main/statement/statement.component';
import { TaxesComponent } from './main/taxes/taxes.component';

const routes: Routes = [
  {
    path: '', component: MainComponent,
    children: [
      {
        path: 'scheduler',
        component: SchedulerComponent
      },
      {
        path: 'statement',
        component: StatementComponent
      },
      {
        path: 'taxes',
        component: TaxesComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
