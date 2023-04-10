import {Component, OnInit, Output, EventEmitter, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {Project} from '../core/models/project.interface';
import {ProjectService} from "../core/services/project.service";
import {LayoutComponent} from "../layout/layout.component";
import {PaginatorService} from "../core/services/paginator.service";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})
export class ProjectComponent implements OnInit {
  searchTerm = '';
  loading = true;
  dataSource: MatTableDataSource<Project>;
  displayedColumns = ['id', 'name', 'description', 'dateCreated'];

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private projectService: ProjectService, private layout: LayoutComponent, private paginatorService: PaginatorService) {
    this.dataSource = new MatTableDataSource();
    this.paginatorService.updateDataSource(this.dataSource);
  }


  ngOnInit(): void {
    this.projectService.getAllProjects().subscribe((projects: Project[]) => {
      this.dataSource.data = projects;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
      this.loading = false;
      this.layout.dataSource = this.dataSource;
    });

    this.dataSource.filterPredicate = (data: Project, filter: string) => {
      const nameMatch = data.name.toLowerCase().includes(filter.toLowerCase());
      const descriptionMatch = data.description.toLowerCase().includes(filter.toLowerCase());
      return nameMatch || descriptionMatch;
    };
  }
  applyFilter() {
    this.dataSource.filter = this.searchTerm.trim().toLowerCase();
  }
  onSearchResults(projects: Project[]): void {
    this.dataSource.data = projects;
  }

createEntity() {
  // CREATE ENTITY
  console.log('Create new project');
}

reloadProjects(): void {
  this.loading = true;
  this.projectService.getAllProjects().subscribe((projects: Project[]) => {
    this.dataSource.data = projects;
    this.loading = false;
  });
}


}
