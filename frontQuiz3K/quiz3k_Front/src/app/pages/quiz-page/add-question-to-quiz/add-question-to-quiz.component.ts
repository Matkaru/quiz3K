import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {QuizService} from "../../../service/quiz.service";
import {QuestionService} from "../../../service/question.service";



@Component({
  selector: 'app-add-question-to-quiz',
  templateUrl: './add-question-to-quiz.component.html',
  styleUrls: ['./add-question-to-quiz.component.css']
})
export class AddQuestionToQuizComponent implements OnInit{
  quizName: string;
  questions: string[];

  constructor(
    private route: ActivatedRoute,
    private quizService: QuizService,
    private questionService: QuestionService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const quizId = params.get('quizId');
      this.getQuizName(quizId);
      this.getQuestions(quizId);
    });
  }

  getQuizName(quizId: string): void {
    this.quizService.getQuizName(quizId).subscribe(
      response => {
        this.quizName = response.name;
      },
      error => {
        console.error('Błąd podczas pobierania nazwy quizu:', error);
      }
    );
  }

  getQuestions(quizId: string): void {
    this.questionService.getQuestionsByQuiz(quizId).subscribe(
      response => {
        this.questions = response.map(question => question.questionText);
      },
      error => {
        console.error('Błąd podczas pobierania pytań:', error);
      }
    );
  }

  addNewQuestion(): void {
    // Obsługa dodawania nowego pytania
    // ...
  }
}

