import { Component } from '@angular/core';

@Component({
  selector: 'app-statement',
  templateUrl: './statement.component.html',
  styleUrls: ['./statement.component.css']
})
export class StatementComponent {


  schedules = [
    {
      source: '123',
      destination: '456',
      amount: 100
    },
    {
      source: '12345456',
      destination: '45654654',
      amount: 101
    }
  ]


  onViewSchedule(item: any) {
    console.log(item);
  }
}
