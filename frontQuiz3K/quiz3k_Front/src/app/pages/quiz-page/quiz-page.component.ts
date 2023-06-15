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
  quizName: string = 'quiz Name';
  quizList: Quiz[] = [];
  deleteConfirmation: boolean = false;
  quizToDeleteId: number | null = null;
  editedQuizName: string = '';
  editedQuizId: number | null = null;


  constructor(private router: Router, private quizService: QuizService, private authService: AuthService) {
  }


  addQuiz() {
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

  logout() {
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
        console.error('An error occurred while downloading the quiz list:', error);
      }
    );
  }

  editQuiz(quiz) {
    this.editedQuizId = quiz.id;
    this.editedQuizName = quiz.quizName;
  }
  updateQuizName(quizId: number) {
    const updatedQuiz = this.quizList.find(quiz => quiz.id === quizId);
    if (updatedQuiz) {
      updatedQuiz.quizName = this.editedQuizName;
      this.quizService.updateQuiz(updatedQuiz).subscribe(() => {
        this.loadQuizList();
      });
      this.resetForm();
    }
  }

  deleteQuiz(quizId: number) {
    if (this.authService.isLoggedUser()) {
      this.deleteConfirmation = true;
      this.quizToDeleteId = quizId;
    } else {
      console.log("The user is not authenticated");
    }
  }

  cancelDelete() {
    this.deleteConfirmation = false;
    this.quizToDeleteId = null;
    this.editedQuizName = '';
  }
  resetForm() {
    this.editedQuizId = null;
    this.editedQuizName = '';
  }

  confirmDelete(){
    if (this.quizToDeleteId) {
      this.quizService.deleteQuiz(this.quizToDeleteId).subscribe(
        () => {
          this.loadQuizList();
          this.deleteConfirmation = false;
          this.quizToDeleteId = null;
        },
        (error: any) => {
          console.error('An error occurred while deleting the quiz:', error);
        }
      );
    }
  }

  cancelEdit() {
    this.editedQuizId = null;
    this.editedQuizName = '';
  }
  goToAddQuestionToQuiz(quizId, quizName: string) {
    this.router.navigate(['add-question-to-quiz', quizId, quizName]);
  }

}
