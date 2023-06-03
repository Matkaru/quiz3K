import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {User} from "../pages/registration-page/user.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api/users';

  constructor(private http: HttpClient) { }

  createUser(user: User): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}`, user);
  }

  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}`);
  }

  getUser(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}-user`);
  }

  activateUser(confirmationToken: string): Observable<void> {
    return this.http.get<void>(`${this.apiUrl}/activate-user/${confirmationToken}`);
  }
}
