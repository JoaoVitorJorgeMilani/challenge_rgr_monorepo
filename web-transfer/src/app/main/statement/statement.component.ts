import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { StatementService } from './statement.service';
import { PaginationConfig } from 'src/app/shared/table/pagination-config-model';
import { AlertComponent } from 'src/app/shared/alert/alert.component';
import { Router } from '@angular/router';
import { StatementViewComponent } from './statement-view/statement-view.component';

@Component({
  selector: 'app-statement',
  templateUrl: './statement.component.html',
  styleUrls: ['./statement.component.css']
})
export class StatementComponent implements AfterViewInit {
  
  @ViewChild(AlertComponent) alert!: AlertComponent;
  
  schedules = []
  selectedScheduleId: any;

  paginationConfig : PaginationConfig = {
    itemsPerPage : 6,
    currentPage : 1,
    totalItems : 0
  };

  activeView: boolean = false;
  activeEdit: boolean = false;

  constructor(private service: StatementService, private router : Router) { }

  ngAfterViewInit(): void {
    this.loadSchedules();
  }


  loadSchedules() {
    this.alert.clearError();

    this.service.getSchedulesPageable(this.paginationConfig).subscribe({
      next: (response) => {
        this.schedules = response.content;
        this.configPagination(response);
      },
      error: () => {
        this.alert.addErrorMessage("Erro ao buscar os agendamentos, contate o administrador");
      }
    });
  }

  configPagination(response: any) {
    this.paginationConfig.totalItems = response.totalElements;
  }

  onPageChange(event: any) {
    this.paginationConfig.currentPage = event;
    this.loadSchedules();
  }


  onViewSchedule(schedule: any) {
    this.selectedScheduleId = schedule.encryptedId;
    this.activeView = true;    
  }

  onEditSchedule(schedule: any) {
    console.log(schedule);
  }

  onDeleteSchedule(schedule: any) {
    this.alert.clear();
    this.service.deleteSchedule(schedule.encryptedId).subscribe({
      next: (response) => {
        this.alert.addSuccessMessage("Agendamento excluído com sucesso!");
        this.loadSchedules();
      },
      error: (error) => {
        this.alert.addErrorMessage("Erro ao excluir o agendamento, contate o administrador");
      }
    })
  }
}
