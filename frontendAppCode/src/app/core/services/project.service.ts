import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Project} from "../models/project.interface";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private apiUrl = 'http://localhost:8080/api/v1/projects';

  constructor(private http: HttpClient) {
  }

  getAllProjects(): Observable<Project[]> {
    // return this.http.get<Project[]>(this.apiUrl);
    const project: Project = {name:'Projet1', description:'dadea ', complexities:[], id:1 , user:undefined, active: true, dateCreated: new Date(), lastUpdated: new Date()}
    return of<Project[]>([project]);
  }

  addProject(project: Project): Observable<Project> {
    return this.http.post<Project>(this.apiUrl, project);
  }

  searchProjects(query: string): Observable<Project[]> {
    const params = new HttpParams().set('search', query);
    return this.http.get<Project[]>(`${this.apiUrl}/search`, { params });
  }
}
