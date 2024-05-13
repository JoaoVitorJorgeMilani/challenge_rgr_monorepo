import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './main/main.component';
import { SchedulerComponent } from './main/scheduler/scheduler.component';
import { StatementComponent } from './main/statement/statement.component';

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
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
