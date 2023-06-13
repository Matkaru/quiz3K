import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import { QuizService } from "../../../service/quiz.service";
import { QuestionService } from "../../../service/question.service";
import {Answer, Question} from "./question.model";

@Component({
  selector: 'app-add-question-to-quiz',
  templateUrl: './add-question-to-quiz.component.html',
  styleUrls: ['./add-question-to-quiz.component.css']
})
export class AddQuestionToQuizComponent implements OnInit {

  quizName: string;
  newQuestion: { correctAnswerId: string; quizId: string; answers: any[]; id: string; questionText: string };

  answer: { correct: boolean; id: string; text: string } = {
    id: '',
    text: '',
    correct: false
  };
  private correctAnswerId: string;
  questionList: any;

  constructor(private activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      console.log('parametry' + params)
      this.quizName = params['quizName'];
      console.log(this.quizName);
    });
    this.newQuestion = {
      id: '',
      quizId: '',
      questionText: '',
      answers: [],
      correctAnswerId: ''
    };
  }

  addQuestion() {
    if (this.newQuestion.questionText && this.newQuestion.answers.length > 0 && this.correctAnswerId) {
      this.newQuestion.correctAnswerId = this.correctAnswerId;
    } else {
      console.log('Wypełnij wszystkie pola');
    }
  }

  addAnswer() {
    this.newQuestion.answers.push({ id: '', text: '', correct: false });
  }
  setCorrectAnswer(answer: Answer) {
    this.newQuestion.answers.forEach(a => a.correct = (a === answer));
    this.newQuestion.correctAnswerId = String(answer.id);
  }
}



