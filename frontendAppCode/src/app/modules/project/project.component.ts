import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {ProjectService} from "../../services/project.service";
import {NotificationService} from '../../services/notification.service';
import {Project} from "../../models/project";

@Component({
  selector: 'app-project',
  templateUrl: '../../components/project/project.component.html',
  styleUrls: ['../../components/project/project.component.scss'],
  // only run change detection when event or @Input() property changes
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProjectComponent implements OnInit {
  projects: Project[] = [];
  filteredProjects: Project[] = [];
  searchQuery = '';
  isLoading = false;

  constructor(private projectService: ProjectService, private notificationService: NotificationService) {
  }

  ngOnInit(): void {
    // ...
    this.loadProjects();
  }


  loadProjects(): void {
    this.isLoading = true;
    this.projectService.getProjects().subscribe((projects) => {
      this.projects = projects;
      this.filteredProjects = projects;
      this.isLoading = false;
    });
  }


  searchProjects(): void {
    this.projectService.searchProjects(this.searchQuery).subscribe((projects) => {
      this.projects = projects;
    });
  }

  onSearch(query: string): void {
    if (!query) {
      this.filteredProjects = this.projects;
    } else {
      this.filteredProjects = this.projects.filter((project) =>
        project.name.toLowerCase().includes(query.toLowerCase())
      );
    }
  }
}
