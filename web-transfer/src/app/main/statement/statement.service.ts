import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PaginationConfig } from 'src/app/shared/table/pagination-config-model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StatementService {

  baseUrl = environment.apiUrl;
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
  
  constructor(private httpClient: HttpClient) { }

  getSchedulesPageable(pagination: PaginationConfig): Observable<any> {
    var paginationParams = {
      page: (pagination.currentPage > 0) ? pagination.currentPage - 1 : pagination.currentPage,
      size: pagination.itemsPerPage
    }

    return this.httpClient.get(`${this.baseUrl}/transfer/list`, {params: paginationParams});
  }

  deleteSchedule(encryptedId : string): Observable<any> {
    var encoded = encodeURIComponent(encryptedId)
    return this.httpClient.delete(`${this.baseUrl}/transfer/delete?encryptedId=${encoded}`);
  }

  getSchedule(encryptedId : string): Observable<any> {
    var encoded = encodeURIComponent(encryptedId)
    return this.httpClient.get(`${this.baseUrl}/transfer/get?encryptedId=${encoded}`);
  }
}


