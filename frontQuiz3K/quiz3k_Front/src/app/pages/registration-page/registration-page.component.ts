import {Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {UserService} from "../../service/user.service";
import {User} from "./user.model";
import {FormArray, FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../auth-page/auth.service";
import {timer} from "rxjs";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit{

  errorMessage: string | null = null;
  email: string = '';
  password: string = '';
  userType: string = '';
  signupForm: FormGroup;
  userExists: boolean = false;
  showSuccessMessage: boolean = false;

  constructor(private authService: AuthService, private userService: UserService, private router: Router) {

  }

  registerUser() {

    if (this.userType === '') {
      this.errorMessage = 'Wybierz typ użytkownika.';
      return;
    }

    let user: { password: string; userType: string; email: string } = {
      email: this.email,
      password: this.password,
      userType: this.userType,
    };

    this.userService.getAllUsers(user).subscribe(users => {
      if (users.some(u => u.email === user.email)) {
        this.userExists = true;
        console.log("The user already exists");
      } else {
        this.authService.logout();
        this.authService.createNewUser(user).subscribe(() => {
          this.showSuccessMessage = true;
          timer(5000).subscribe(() => {
            this.showSuccessMessage = false;
            this.router.navigate(['/', 'logowanie']);
          });
          },
          (error: any) => {
            this.errorMessage = error;
          });
      }
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
