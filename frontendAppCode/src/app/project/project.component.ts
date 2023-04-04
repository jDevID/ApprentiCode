import {Component, OnInit, Output, EventEmitter, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {Project} from '../core/models/project.interface';
import {ProjectService} from "../core/services/project.service";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {
  searchTerm = '';
  loading = true;
  dataSource: MatTableDataSource<Project>;
  displayedColumns = ['id', 'name', 'description', 'description', 'dateCreated'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private projectService: ProjectService) {
    this.dataSource = new MatTableDataSource();
  }

  ngOnInit(): void {
    this.projectService.getAllProjects().subscribe((projects: Project[]) => {
      this.dataSource.data = projects;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      this.loading = false;
    });
  }

  onSearchResults(projects: Project[]): void {
    this.dataSource.data = projects;
  }

  reloadProjects(): void {
    this.loading = true;
    this.projectService.getAllProjects().subscribe((projects: Project[]) => {
      this.dataSource.data = projects;
      this.loading = false;
    });
  }

}
