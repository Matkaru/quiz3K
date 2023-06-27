import { Component } from '@angular/core';
import { FirstPageComponent } from './pages/first-page.component';
import {Router} from "@angular/router";
import {AuthService} from "./pages/auth-page/auth.service";
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'quiz3k_Front';
  constructor(private router: Router, private authService: AuthService) {}

  getRedirectLink(): string {
    if (this.authService.isLoggedUser()) {
      return '/quiz';
    } else {
      return '';
    }
  }
}
