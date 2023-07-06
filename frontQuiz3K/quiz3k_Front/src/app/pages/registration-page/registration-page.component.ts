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
export class RegistrationPageComponent implements OnInit {

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

    if (!this.email || !this.password) {
      this.errorMessage = 'Uzupełnij wszystkie pola';
      return;
    }

    let user: { password: string; email: string } = {
      email: this.email,
      password: this.password,
    };

    this.userService.getAllUsers(user).subscribe(users => {
      if (users.some(u => u.email === user.email)) {
        this.userExists = true;
        console.log("The user already exists");
      } else {
        this.authService.logout();
        this.authService.createNewUser(user).subscribe(() => {
            this.showSuccessMessage = true;
            timer(3000).subscribe(() => {
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

  changeFact() {
    // Definiowanie tablicy z różnymi ciekawostkami
    var facts = [
      "Sokół Wędrowny jest najszybszym stworzeniem na świecie. W locie pikującym może osiągnąć prędkość zbliżoną do 360 km/h.",
      "Korea Północna i Kuba to jedyne miejsca, w których nie można kupić Coca-Coli.",
      "Środkowy palec Galileusza jest przechowywany w Muzeum Nauki we Florencji.",
      "Hiszpańskie słowo „esposas” oznacza zarówno „kajdanki”, jak i „żony”",
      "Kiedy bierzesz gorącą kąpiel, spalasz tyle kalorii, ile byś spalił na 30-minutowym spacerze."
    ];

    // Definiowanie tablicy z różnymi ścieżkami do obrazów
    var images = [
      "assets/sokol.jpg",
      "assets/s-l500.jpg",
      "assets/palec.JPG",
      "assets/kajdanki.jpg",
      "assets/kapiel.JPG"
    ];

    // Losowe wybieranie ciekawostki i obrazu przy ładowaniu strony
    var randomIndex = Math.floor(Math.random() * facts.length);
    document.getElementById("randomFact").innerHTML = facts[randomIndex];
    document.getElementById("randomImage").setAttribute("src", images[randomIndex]);

    // Funkcja do dynamicznej zmiany ciekawostki i obrazu przy kliknięciu
    var changeFact = () => {
      var newIndex = Math.floor(Math.random() * facts.length);
      while (newIndex === randomIndex) {
        newIndex = Math.floor(Math.random() * facts.length);
      }
      randomIndex = newIndex;
      document.getElementById("randomFact").innerHTML = facts[randomIndex];
      document.getElementById("randomImage").setAttribute("src", images[randomIndex]);
    };
  }

  ngOnInit() {
    this.changeFact();
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


