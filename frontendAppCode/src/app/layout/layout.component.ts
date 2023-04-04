import { ProjectService } from '../core/services/project.service';
import {Project} from "../core/models/project.interface";
import { Component, EventEmitter, Output } from '@angular/core';
@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent {
  searchQuery: string = '';
  @Output() searchResults: EventEmitter<Project[]> = new EventEmitter();

  constructor(private projectService: ProjectService) {}
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

}
