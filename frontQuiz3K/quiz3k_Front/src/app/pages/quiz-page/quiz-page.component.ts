import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Quiz} from "./quiz.model";
import {QuizService} from "../../service/quiz.service";
import {observableToBeFn} from "rxjs/internal/testing/TestScheduler";
import {AuthService} from "../auth-page/auth.service";
import {HttpHeaders} from '@angular/common/http';


@Component({
  selector: 'app-quiz-page',
  templateUrl: './quiz-page.component.html',
  styleUrls: ['./quiz-page.component.css']
})
export class QuizPageComponent implements OnInit {
  id: number = Number('');
  quizName: string = '';
  quizList: Quiz[] = [];

  constructor(private router: Router, private quizService: QuizService, private authService: AuthService) {
  }


  dodajQuiz() {
    let quiz: Quiz = {
      id: null,
      quizName: this.quizName
    };
    console.log(quiz);
    this.quizService.createQuiz(quiz).subscribe(x => {
      this.router.navigate(['/', 'api', '/', 'quiz'])
      window.location.reload();
    });
  }

  wyloguj() {
    this.authService.logout();
    this.router.navigate(['']);
  }

  ngOnInit(): void {
    this.quizList = [
      {id: this.id, quizName: this.quizName},
    ];
    this.loadQuizList();
  }

  private loadQuizList() {
    this.quizService.getAllQuiz().subscribe(
      (quizzes: Quiz[]) => {
        console.log(quizzes);
        this.quizList = quizzes;
      },
      (error: any) => {
        console.error('Wystąpił błąd podczas pobierania listy quizów:', error);
      }
    );
  }

  edytujQuiz(quiz) {

  }

  usunQuiz(quizId: number) {
    if (this.authService.isLoggedUser()) {
      this.quizService.deleteQuiz(quizId).subscribe(
        () => {
          this.loadQuizList();
        },
        (error: any) => {
          console.error('Wystąpił błąd podczas usuwania quizu:', error);
        }
      );
    } else {
      console.log("Użytkownik nie jest uwierzytelniony, podejmij odpowiednie działania");
    }
  }

}
