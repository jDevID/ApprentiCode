import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { ApiService } from './api.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private readonly usersUrl = 'users';
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private apiService: ApiService, private http: HttpClient) {}

  getUsers(pageIndex: number, pageSize: number): Observable<User[]> {
    return this.apiService.get<User[]>(this.usersUrl);
  }

  // Implement other methods to interact with the User API as needed
    updateUser(user: User): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${user.id}`, user);
  }
}
