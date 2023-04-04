import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Complexity } from '../models/complexity.interface';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComplexityService {
  private apiUrl = 'http://localhost:8080/api/v1/complexities';

  constructor(private http: HttpClient) {}

  getAllComplexities(): Observable<Complexity[]> {
    return this.http.get<Complexity[]>(this.apiUrl);
  }

  addComplexity(complexity: Complexity): Observable<Complexity> {
    return this.http.post<Complexity>(this.apiUrl, complexity);
  }

  searchComplexities(query: string): Observable<Complexity[]> {
    const params = new HttpParams().set('search', query);
    return this.http.get<Complexity[]>(`${this.apiUrl}/search`, { params });
  }
}
