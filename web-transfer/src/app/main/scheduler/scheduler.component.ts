import { Component } from '@angular/core';

@Component({
  selector: 'app-scheduler',
  templateUrl: './scheduler.component.html',
  styleUrls: ['./scheduler.component.css']
})
export class SchedulerComponent {

  schedule = {
    source: '654654',
    destiny: '654654654',
    amount: 321654,
    transferDate: '',
    fee: 666.67
  };

  currentDate = new Date();

  onSubmit() {
    console.log(this.schedule);
  }
}
