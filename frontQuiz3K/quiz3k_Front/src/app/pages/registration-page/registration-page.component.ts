import {Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "./user.model";
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../auth-page/auth.service";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit{
  email: string = '';
  password: string = '';
  // @ts-ignore
  signupForm: FormGroup;


  constructor(private authService: AuthService, private router: Router) {

  }

  registerUser() {
    let user: User = {
      email: this.email,
      password: this.password
    };
    console.log(user);
    this.authService.logout();
    this.authService.createNewUser(user).subscribe(x => {
      this.router.navigate(['/', 'logowanie']);
    });
  }

  ngOnInit() {
    this.signupForm = new FormGroup({
      'userData': new FormGroup({
        'mail': new FormControl(),
        'password': new FormControl()
      })
    });
    this.signupForm.statusChanges.subscribe(
      (status) => console.log(status)
    );
  }
}
