import {ProjectService} from '../core/services/project.service';
import {Project} from "../core/models/project.interface";
import {Component, EventEmitter, Output} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {PaginatorService} from "../core/services/paginator.service";
import {ActionButtons} from "../core/models/action-buttons.interface";
@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent {
  searchQuery: string = '';
  dataSource: MatTableDataSource<any> = new MatTableDataSource<any>();
  @Output() searchResults: EventEmitter<Project[]> = new EventEmitter();

  buttons: ActionButtons[] = [
    {
      label: 'Add Project',
      color: 'primary',
      onClick: () => this.onCreateEntity(),
    },
    {
      label: 'Reload Projects',
      color: 'primary',
      onClick: () => this.reloadProjects(),
    },
  ];

  constructor(private projectService: ProjectService, private paginatorService: PaginatorService) {
    this.paginatorService.dataSource$.subscribe((dataSource) => {
      if (dataSource) {
        this.dataSource = dataSource;
      } else {
        this.dataSource = new MatTableDataSource<any>();
      }
    });
  }

  search(): void {
    console.log('Search query:', this.searchQuery);
    this.projectService.searchProjects(this.searchQuery).subscribe((projects: Project[]) => {
      this.searchResults.emit(projects);
    });
  }

  clearSearch(): void {
    this.searchQuery = '';
    this.projectService.getAllProjects().subscribe((projects: Project[]) => {
      this.searchResults.emit(projects);
    });
  }
  onCreateEntity() {
    // CREATE ENTITY
    console.log('Create new project');
  }

  reloadProjects(): void {
    this.projectService.getAllProjects().subscribe((projects: Project[]) => {
      this.dataSource.data = projects;
    });
  }
}
