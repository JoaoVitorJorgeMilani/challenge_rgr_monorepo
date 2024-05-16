import { Component, ViewChild } from '@angular/core';
import { AlertComponent } from 'src/app/shared/alert/alert.component';
import { SchedulerService } from './scheduler.service';

@Component({
  selector: 'app-scheduler',
  templateUrl: './scheduler.component.html',
  styleUrls: ['./scheduler.component.css']
})
export class SchedulerComponent {

  @ViewChild(AlertComponent) alert!: AlertComponent;

  schedule = {
    source: '',
    destiny: '',
    amount: 0,
    transferDate: '',
    fee: 0
  };
  feeCalculated: boolean = false;

  currentDate = new Date();

  get total() : number { 
    return (this.schedule.amount >= 0 && this.schedule.fee >= 0) ? this.schedule.amount + this.schedule.fee : 0;
  }
  
  constructor(private service: SchedulerService) { }

  onSubmit() {
    console.log(this.schedule);
  }

  calculateFeeFromInput() {
    console.log("calculating from input")

    if(this.schedule.amount > 0 && !Number.isNaN(Date.parse(this.schedule.transferDate)) && Date.parse(this.schedule.transferDate) >= 0) {
      this.calculateFee();
    }
  }

  calculateFee() {
    if (this.validateFeeCalculation()) {
      this.service.getTax(this.schedule).subscribe(
        {
          next: (tax) => {
            this.schedule.fee = tax;
            this.feeCalculated = true;
          },
          error: error => {
            if(error.status){
              this.handleError(error);
            } else {
              this.alert.clear();
                this.alert.addErrorMessage("Erro ao realizar o calculo da taxa de transferencia, contate o administrador");
            }            
          }
        }
      )
    }
  }

  validateFeeCalculation() : boolean {
    this.alert.clear();

    if (this.schedule.amount == null || this.schedule.amount <= 0) {
      this.alert.addErrorMessage('Por gentileza, selecione o valor para calcular a taxa');
    }

    if (Number.isNaN(Date.parse(this.schedule.transferDate)) || Date.parse(this.schedule.transferDate) <= 0) {
      this.alert.addErrorMessage('Por gentileza, selecione a data para calcular a taxa');
    }

    return !this.alert.hasError();
  }

  validateSchedule(showAlert: boolean = true): boolean {
    if(showAlert)
      this.alert.clear();

    if (this.schedule.source.length != 10) {
      if(!showAlert) return false;
      this.alert.addErrorMessage('Origem inválida');
    }

    if (this.schedule.destiny.length != 10) {
      if(!showAlert) return false;
      this.alert.addErrorMessage('Destino inválido');
    }

    if (this.schedule.amount == null || this.schedule.amount <= 0) {
      if(!showAlert) return false;
      this.alert.addErrorMessage('Valor inválido');
    }

    if (Number.isNaN(Date.parse(this.schedule.transferDate)) || Date.parse(this.schedule.transferDate) <= 0) {
      if(!showAlert) return false;
      this.alert.addErrorMessage('Data inválida');
    }

    if (!this.feeCalculated) {
      if(!showAlert) return false;
      this.alert.addErrorMessage('Por gentileza, realize o calculo da taxa');
    }

    return !this.alert.hasError();
  }

  handleError(error: any) {
    this.alert.clear();

    switch(error.status) { 
      case 406: { 
        this.alert.addErrorMessage("Taxa para essa transferencia não encontrada, por gentileza, informe outra data para transferência");
        break; 
      } 
      default: { 
        this.alert.addErrorMessage("Erro ao realizar o calculo da taxa de transferencia, contate o administrador");
         break; 
      }
   }
  }





}
