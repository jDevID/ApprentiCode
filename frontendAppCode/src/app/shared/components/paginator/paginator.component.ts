import {Component, EventEmitter, Input, Output} from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { PageEvent } from '@angular/material/paginator';
@Component({
  selector: 'app-paginator',
  templateUrl: './paginator.component.html',
  styleUrls: ['./paginator.component.scss'],
})
export class PaginatorComponent {
  @Input() dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();
  @Output() pageEvent = new EventEmitter<any>();

  pageSizeOptions = [5, 10, 20];

  onPageChange(event: PageEvent): void {
    if (this.dataSource && this.dataSource.paginator) {
      this.dataSource.paginator.pageSize = event.pageSize;
      this.dataSource.paginator.pageIndex = event.pageIndex;
      this.dataSource.paginator.firstPage();
    }
  }

}
