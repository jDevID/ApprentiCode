import { Injectable } from '@angular/core';
// search
import { HttpClient, HttpParams } from '@angular/common/http';
import {ProjectInterface} from "../interfaces/project.interface";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  // // ...
  // searchProjects(query: string): Observable<ProjectInterface[]> {
  //   const params = new HttpParams().set('search', query);
  //   return this.http.get<ProjectInterface[]>(`${this.apiUrl}/search`, { params });
  // }
  //

  constructor() { }
}
