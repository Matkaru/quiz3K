import {Router} from '@angular/router';
import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "../registration-page/user.model";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  email: string = '';
  password: string = '';

  constructor(private userService: UserService, private router: Router){
  }

  logInUser() {
    let user: User = {
      email: this.email,
      password: this.password
    };
    console.log(user);
    this.userService.getUser(user).subscribe(x =>{this.router.navigate(['/','quizy']);});
  }
}
