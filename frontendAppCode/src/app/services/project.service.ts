import { Injectable } from '@angular/core';
// search
import { HttpClient, HttpParams } from '@angular/common/http';
import {Project} from "../models/project";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  // ...
  searchProjects(query: string): Observable<Project[]> {
    const params = new HttpParams().set('search', query);
    return this.http.get<Project[]>(`${this.apiUrl}/search`, { params });
  }


  constructor() { }
}
