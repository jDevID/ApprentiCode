import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { EntityService } from '../../services/entity.service';
import { Project } from '../../interfaces/project.interface';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {
  dataSource: MatTableDataSource<Project>;
  displayedColumns = ['id', 'name', 'description'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private entityService: EntityService) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.entityService.getProjects().subscribe((projects: Project[]) => {
      this.dataSource.data = projects;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    });
  }
}
