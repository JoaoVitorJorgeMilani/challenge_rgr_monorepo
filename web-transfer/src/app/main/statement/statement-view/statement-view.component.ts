import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { StatementService } from '../statement.service';

@Component({
  selector: 'app-statement-view',
  templateUrl: './statement-view.component.html',
  styleUrls: ['./statement-view.component.css']
})
export class StatementViewComponent {

  @Input() encryptedId?: string;
  schedule = {
    source : '',
    destination : '',
    amount : 0,
    transferDate : new Date,
    tax : 0,
    createdAt: '',
    updatedAt: ''
  }

  constructor(private route: ActivatedRoute, private service: StatementService ) { }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['encryptedId'] && this.encryptedId) {
      this.loadStatement();
    }
  }


  loadStatement(){
    if(this.encryptedId){
      this.service.getSchedule( this.encryptedId ).subscribe({
        next: (response) => {
          this.schedule = response;
        },
        error: (error) => {
          console.log(error);
        }
      })
    }
  }

}
