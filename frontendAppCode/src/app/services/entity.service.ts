import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../interfaces/project.interface';
import { Complexity } from '../interfaces/complexity.interface';
import { Entry } from '../interfaces/entry.interface';
import { Resource } from '../interfaces/resource.interface';
import { Tag } from '../interfaces/tag.interface';
import { User } from '../interfaces/user.interface';


@Injectable({
  providedIn: 'root'
})
export class EntityService {
  private apiUrl = 'http://localhost:3000/api/v1'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/projects`);
  }
  getComplexities(): Observable<Complexity[]> {
    return this.http.get<Complexity[]>(`${this.apiUrl}/complexities`);
  }

  getEntries(): Observable<Entry[]> {
    return this.http.get<Entry[]>(`${this.apiUrl}/entries`);
  }

  getResources(): Observable<Resource[]> {
    return this.http.get<Resource[]>(`${this.apiUrl}/resources`);
  }
}
