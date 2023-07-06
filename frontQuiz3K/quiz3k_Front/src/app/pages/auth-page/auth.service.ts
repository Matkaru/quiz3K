import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable, switchMap, throwError} from "rxjs";
import {tap} from "rxjs/operators";
import {User} from "../registration-page/user.model";


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  currentUser: User;

  constructor(private httpClient: HttpClient, private router: Router) {
    this.currentUser = JSON.parse(localStorage.getItem('userData'));
  }

  login(email: string, password: string): Observable<any> {
    localStorage.removeItem('userData');
    this.currentUser = null;

    return this.httpClient
      .get<any>('http://localhost:8080/api/users-user', {
        headers: {Authorization: 'Basic ' + btoa(email + ':' + password)}
      })
      .pipe(
        tap<any>((response) => {
          this.currentUser = {
            email: email,
            password: password,

          };
          localStorage.setItem('userData', JSON.stringify(this.currentUser));
        })
      );
  }

  createNewUser(newUser: User): Observable<any> {
    return this.httpClient.post<any>(
      'http://localhost:8080/api/users',
      newUser
    );
  }

  logout(): void {
    this.currentUser = null;
    localStorage.removeItem('userData');
  }

  isLoggedUser(): boolean {
    return !!this.currentUser;
  }

  getBasicAuthToken(): string {
    return btoa(this.currentUser.email + ':' + this.currentUser.password);
  }
}
