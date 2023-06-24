import {Component, OnInit} from '@angular/core';
import {AuthService} from "../auth-page/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {QuizService} from "../../service/quiz.service";
import {Answer, Question} from "../quiz-page/add-question-to-quiz/question.model";
import {QuestionService} from "../../service/question.service";
import {AnswerService} from "../../service/answer.service";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-quiz-statistics',
  templateUrl: './quiz-statistics.component.html',
  styleUrls: ['./quiz-statistics.component.css']
})
export class QuizStatisticsComponent implements OnInit {
  quizName: string;
  quizId: number;
  questionList: any[] = [];

  constructor(private router: Router,
              private authService: AuthService,
              private activatedRoute: ActivatedRoute,
              private questionService: QuestionService,
              private answerService: AnswerService,
              private http: HttpClient,) {
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['']);
  }

  quizPage() {
    this.router.navigate(['quiz']);
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params) => {
      this.quizId = params['quizId'];
      this.quizName = params['quizName'];
      this.getQuestionsByQuiz()
    });
  }

  getQuestionsByQuiz() {
    this.questionService.getQuestionsByQuiz(this.quizId)
      .subscribe(
        (questions: Question[]) => {
          this.questionList = questions;
          this.loadAnswersForQuestions();
        },
        (error) => {
          console.error('Wystąpił błąd podczas pobierania pytań:', error);
        }
      );
  }
  loadAnswersForQuestions() {
    if (this.questionList) {
      for (const question of this.questionList) {
        this.getAnswersByQuestionId(question.id);
      }
    }
  }

  getAnswersByQuestionId(questionId: number) {
    this.answerService.getAnswersByQuestion(questionId).subscribe(
      (answers: Answer[]) => {
        this.assignAnswersToQuestion(questionId, answers);
      },
      (error) => {
        console.error('Wystąpił błąd podczas pobierania odpowiedzi:', error);
      }
    );
  }

  assignAnswersToQuestion(questionId: number, answers: Answer[]) {
    const question = this.questionList.find(q => q.id === questionId);
    if (question) {
      question.answers = answers;
    }
  }
}
