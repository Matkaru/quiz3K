import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {QuestionService} from "../../service/question.service";
import {AuthService} from "../auth-page/auth.service";
import {AnswerService} from "../../service/answer.service";
import {Answer, Question} from "../quiz-page/add-question-to-quiz/question.model";
import {CopyQuizeService} from "../../service/copy.quize.service";

@Component({
  selector: 'app-share-project',
  templateUrl: './share-project.component.html',
  styleUrls: ['./share-project.component.css']
})
export class ShareProjectComponent {
  copyQuizName: string;
  quizId: number;
  questionForm: FormGroup;
  copyQuestion: any;
  questionList: any[] = [];
  private authService: any;
  private router: Router;
  private questionService: QuestionService;
  private id: number;
  showCheckboxInfo: boolean;

  constructor(private formBuilder: FormBuilder) {
    this.copyQuizName = '';
    this.quizId = 0;
    this.copyQuestion = {};
    this.questionForm = this.formBuilder.group({
      questionText: ['', Validators.required],
      questionType: ['', Validators.required]
    });
  }

  addQuestion() {
    // Logika dodawania pytania do listy pytań
    const question = {
      questionText: this.questionForm.value.questionText,
      questionType: this.questionForm.value.questionType,
      answers: []
    };
    this.questionList.push(question);
    this.resetQuestionForm();
  }

  resetQuestionForm() {
    // Resetowanie formularza dodawania pytania
    this.questionForm.reset();
  }

  getAnswerForm(index: number) {
    // Pobieranie formularza dla odpowiedzi o danym indeksie
    return this.formBuilder.group({
      confirmedAnswer: false,
      answerForTheQuestion: ['', Validators.required]
    });
  }

  toggleCorrectAnswer(answerForm: FormGroup) {
    // Przełączanie statusu poprawności odpowiedzi
    const confirmedAnswer = answerForm.get('confirmedAnswer');
    confirmedAnswer.setValue(!confirmedAnswer.value);
  }

  removeAnswer(answerForm: FormGroup) {
    // Usuwanie odpowiedzi z pytania
    const index = this.copyQuestion.answers.indexOf(answerForm);
    if (index !== -1) {
      this.copyQuestion.answers.splice(index, 1);
    }
  }

  addAnswer() {
    // Dodawanie nowej odpowiedzi do pytania
    this.copyQuestion.answers = this.copyQuestion.answers || [];
    this.copyQuestion.answers.push(this.getAnswerForm(this.copyQuestion.answers.length));
  }

  editQuestion(questionId: number) {
  }

  deleteQuestion(questionId: number) {
    this.questionService.deleteQuestion(this.id).subscribe(
      () => {
        console.log('Pytanie zostało usunięte');
        window.location.reload();
      },
      (error) => {
        console.error('Wystąpił błąd podczas usuwania pytania:', error);
      }
    );
  }
    logout() {
      this.authService.logout();
      this.router.navigate(['']);
    }

    }



