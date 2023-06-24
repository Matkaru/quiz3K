import { Component } from '@angular/core';
import {AuthService} from "../auth-page/auth.service";
import {Router} from "@angular/router";
import {QuizService} from "../../service/quiz.service";

@Component({
  selector: 'app-quiz-statistics',
  templateUrl: './quiz-statistics.component.html',
  styleUrls: ['./quiz-statistics.component.css']
})
export class QuizStatisticsComponent {

  constructor(private router: Router, private authService: AuthService) {
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['']);
  }

  quizPage() {
    this.router.navigate(['quiz']);
  }
}
