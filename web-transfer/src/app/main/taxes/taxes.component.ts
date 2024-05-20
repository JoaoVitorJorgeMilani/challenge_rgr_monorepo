import { Component, OnInit } from '@angular/core';
import { TaxesService } from './taxes.service';

@Component({
  selector: 'app-taxes',
  templateUrl: './taxes.component.html',
  styleUrls: ['./taxes.component.css']
})
export class TaxesComponent implements OnInit {

  taxes = [];

  constructor(private service: TaxesService) { }


  ngOnInit(): void {
    this.getTaxes();
  }

  getTaxes()  {
    this.service.getTaxes().subscribe(
      {
        next: (taxes) => {
          this.taxes = taxes.sort((a: any, b: any) => a.days_period - b.days_period);;
        },
        error: (error) => {
          console.log(error);
        }
      }
    )
  }
}
