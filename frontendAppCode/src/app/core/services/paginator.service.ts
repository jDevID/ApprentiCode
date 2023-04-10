import { Injectable } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PaginatorService {
  private dataSourceSubject = new BehaviorSubject<MatTableDataSource<any> | null>(null);
  dataSource$ = this.dataSourceSubject.asObservable();

  updateDataSource(dataSource: MatTableDataSource<any>) {
    this.dataSourceSubject.next(dataSource);
  }
}
