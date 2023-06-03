import {Router} from '@angular/router';
import { Component } from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "./user.model";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent {
  email: string = '';
  password: string = '';

  constructor(private userService: UserService, private router: Router){

  }

  registerUser() {
    let user: User = {
      email: this.email,
      password: this.password
    };
    console.log(user);
    this.userService.createUser(user).subscribe(x=>{this.router.navigate(['/','logowanie']);});
  }
}
