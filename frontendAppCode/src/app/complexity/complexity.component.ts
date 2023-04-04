import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ComplexityService } from '../core/services/complexity.service';
import { Complexity } from '../core/models/complexity.interface';

@Component({
  selector: 'app-complexity',
  templateUrl: './complexity.component.html',
  styleUrls: ['./complexity.component.scss']
})
export class ComplexityComponent implements OnInit {
  dataSource: MatTableDataSource<Complexity>;
  displayedColumns = ['id', 'name', 'description'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private complexityService: ComplexityService) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.complexityService.getAllComplexities().subscribe((complexities: Complexity[]) => {
      this.dataSource.data = complexities;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }

  onSearchResults(complexities: Complexity[]): void {
    this.dataSource.data = complexities;
  }
}
