import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {QuizService} from "../../../service/quiz.service";
import {QuestionService} from "../../../service/question.service";
import {Answer, Question} from "./question.model";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-question-to-quiz',
  templateUrl: './add-question-to-quiz.component.html',
  styleUrls: ['./add-question-to-quiz.component.css']
})
export class AddQuestionToQuizComponent implements OnInit {
  questionList: Question[] = [];
  questionForm: FormGroup;
  quizName: string;
  quizId: number;
  questionQuizId: number;
  newQuestion: { correctAnswerId: string; answers: any[]; questionQuizId: string; id: string; questionType: string; questionText: string } = {
    id: '',
    questionText: '',
    answers: [],
    correctAnswerId: '',
    questionType: 'single',
    questionQuizId: ''
  };

  showCheckboxInfo: boolean = false;

  constructor(
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private questionService: QuestionService,

  ) {
    this.questionForm = this.formBuilder.group({
      questionText: ['', Validators.required],
      questionType: ['', Validators.required],
      answers: this.formBuilder.array([])
    });
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.quizId = params['quizId'],
        // this.questionQuizId = params['quizId'];
      this.questionQuizId = params['quizId'];
      this.newQuestion.questionQuizId = this.questionQuizId.toString();
      this.quizName = params['quizName'];
      this.getQuestionsByQuiz();
      console.log(this.questionList)

    });
  }
  getQuestionsByQuiz() {
    this.questionService.getQuestionsByQuiz(this.questionQuizId).subscribe(
      (questions: Question[]) => {
        this.questionList = questions;
        console.log(this.questionList);
      },
      error => {
        console.error('Wystąpił błąd podczas pobierania pytań:', error);
      }
    );
  }

  getAnswerForm(index: number) {
    return (this.questionForm.get('answers') as FormArray).controls[index] as FormGroup;
  }

  addQuestion() {
    if (this.questionForm.invalid) {
      return;
    }

    this.newQuestion.questionText = this.questionForm.value.questionText;
    this.newQuestion.questionType = this.questionForm.value.questionType;
    console.log(this.quizId)
  // && this.newQuestion.correctAnswerId
    if (this.newQuestion.questionText && this.newQuestion.answers.length > 0 &&
      this.newQuestion.questionQuizId) {
      this.questionService.createQuestion(this.newQuestion).subscribe(
        response => {
          console.log('Pytanie zostało zapisane:', response);
          this.questionForm.reset();
          this.newQuestion = {
            id: '',
            questionText: '',
            answers: [],
            correctAnswerId: '',
            questionType: 'single',
            questionQuizId: ''
          };
          this.showCheckboxInfo = false;
        },
        error => {
          console.error('Wystąpił błąd podczas zapisywania pytania:', error);
        }
      );
    } else {
      console.log('Wypełnij wszystkie pola');
    }
  }

  addAnswer() {
    if ((this.questionForm.get('answers') as FormArray).length === 0) {
      this.showCheckboxInfo = true;
    }

    const newAnswerFormGroup = this.formBuilder.group({
      id: '',
      text: '',
      correct: false,
      deleted: false
    });

    (this.questionForm.get('answers') as FormArray).push(newAnswerFormGroup);
    this.newQuestion.answers.push(newAnswerFormGroup.value);
  }

  toggleCorrectAnswer(answerFormGroup: FormGroup) {
    const answerIndex = (this.questionForm.get('answers') as FormArray).controls.findIndex(control => control === answerFormGroup);
    const answer = this.newQuestion.answers[answerIndex];

    answer.correct = !answer.correct;
  }

  removeAnswer(answerFormGroup: FormGroup) {
    const answersArray = this.questionForm.get('answers') as FormArray;
    const answerIndex = answersArray.controls.findIndex(control => control === answerFormGroup);

    if (answerIndex > -1) {
      answersArray.removeAt(answerIndex);
      this.newQuestion.answers.splice(answerIndex, 1);
    }
  }

  updateQuestionType() {
    const questionType = this.questionForm.value.questionType;
    this.showCheckboxInfo = questionType === 'multiple';

  }

}
