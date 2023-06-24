import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {QuestionService} from "../../service/question.service";
import {AnswerService} from "../../service/answer.service";
import {Answer, Question} from "../quiz-page/add-question-to-quiz/question.model";
import {HttpClient} from "@angular/common/http";
import {CopyQuizService} from "../../service/copy-quiz.service";



@Component({
  selector: 'app-share-project',
  templateUrl: './share-project.component.html',
  styleUrls: ['./share-project.component.css']
})
export class ShareProjectComponent implements OnInit {
  quizName: string;
  quizId: number;
  questionList: any[] = [];
  email: string;

  constructor(
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private questionService: QuestionService,
    private answerService: AnswerService,
    private router: Router,
    private http: HttpClient,
    private copyQuizService: CopyQuizService
  ) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((params) => {
      this.quizName = params['quizName'];
      this.quizId = params['quizId'];
      this.getQuestionsByQuiz();
    });
  }

  saveQuiz() {

    const quizData = {
      quizId: this.quizId,
      // questionId: this.selectedAnswerQuestionId,
      email: this.email,
      userAnswerIdList: this.questionList.map(question  => ({
        questionId: question.id,
        answers: question.answers
          .filter(answer => answer.isSelected)
          .map(answer => answer.id)
      }
      ))
    };
    console.log(quizData);
    this.copyQuizService.saveQuiz(quizData)
      .subscribe(
        (response) => {
          console.log('Quiz został zapisany na serwerze:', response);
        },
        (error) => {
          console.error('Wystąpił błąd podczas zapisywania quizu:', error);
        }
      );
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



