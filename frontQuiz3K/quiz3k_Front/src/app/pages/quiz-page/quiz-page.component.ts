import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Quiz} from "./quiz.model";
import {QuizService} from "../../service/quiz.service";
import {observableToBeFn} from "rxjs/internal/testing/TestScheduler";
@Component({
  selector: 'app-quiz-page',
  templateUrl: './quiz-page.component.html',
  styleUrls: ['./quiz-page.component.css']
})
export class QuizPageComponent implements OnInit {
  quizList: Quiz[] = [];

  constructor(private router: Router, private quizService: QuizService) {
  }

  dodajQuiz() {

    this.router.navigate(['/dodaj-quiz']);
  }

  wyloguj() {

    this.router.navigate(['/login']);
  }}
// nie wiem jak to na prawić , już nie miałem czasu tu jest ta smama syytuacja co robiliśmy z tym subscribe
  // ngOnInit(): void {
  //   this.quizService.getAllQuiz().subscribe(
  //     (quizzes: Quiz[] => {
  //       this.quizList = quizzes;
  //     },
  //     (error: any) => {
  //       console.error('Wystąpił błąd podczas pobierania listy quizów:', error);
  //     }
  //   );
  // }


