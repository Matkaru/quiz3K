import {Router} from '@angular/router';
import {Component} from '@angular/core';
import {User} from "../registration-page/user.model";
import {AuthService} from "../auth-page/auth.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authLogin: AuthService, private router: Router) {
  }

  logInUser() {
    let user: User = {
      email: this.email,
      password: this.password
    };

    this.authLogin.login(user.email, user.password).subscribe(
      () => {
        this.router.navigate(['/', 'quiz']);
      },
      (error) => {
        this.errorMessage = 'Nieprawidłowy adres e-mail lub hasło.';
      }
    );
  }
}
