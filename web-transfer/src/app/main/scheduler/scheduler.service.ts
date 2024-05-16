import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SchedulerService {

  
  baseUrl = environment.apiUrl;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }

  constructor(private httpClient: HttpClient) { }

  getTax(schedule: any): Observable<any> {
    let getParams = {
      transferDate: schedule.transferDate,
      amount: schedule.amount
    }

    return this.httpClient.get<any>(`${this.baseUrl}/transfer/calculate`, {params: getParams});
  }
  
  // add(order: any): Observable<any> {
  //   return this.httpClient.post<any>(`${this.baseUrl}/order/add`, order, this.httpOptions);
  // }

  // getList(): Observable<any> {
  //   return this.httpClient.get(`${this.baseUrl}/order/list`, this.httpOptions);
  // }
}