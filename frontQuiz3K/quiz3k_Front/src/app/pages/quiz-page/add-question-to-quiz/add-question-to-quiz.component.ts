import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { QuestionService } from '../../../service/question.service';
import { AnswerService } from '../../../service/answer.service';
import { AuthService } from '../../auth-page/auth.service';
import { Answer, Question } from './question.model';

@Component({
  selector: 'app-add-question-to-quiz',
  templateUrl: './add-question-to-quiz.component.html',
  styleUrls: ['./add-question-to-quiz.component.css']
})
export class AddQuestionToQuizComponent implements OnInit {
  id: number;
  answers: any[] = [];
  questionList: Question[] = [];
  questionForm: FormGroup;
  quizName: string;
  quizId: number;
  questionQuizId: number;
  questionType: string;
  newQuestion: {
    questionQuizId: string;
    id: string;
    questionType: string;
    questionText: string;
    answers: any[];
  } = {
    id: '',
    questionText: '',
    questionType: 'single',
    questionQuizId: '',
    answers: []
  };

  showCheckboxInfo: boolean = false;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder,
    private questionService: QuestionService,
    private authService: AuthService,
    private answerService: AnswerService
  ) {
    this.questionForm = this.formBuilder.group({
      questionText: ['', Validators.required],
      questionType: ['', Validators.required],
      answers: this.formBuilder.array([]),
    });
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['']);
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params) => {
      this.quizId = params['quizId'];
      this.questionQuizId = params['quizId'];
      this.newQuestion.questionQuizId = this.questionQuizId.toString();
      this.quizName = params['quizName'];
      this.getQuestionsByQuiz();
    });
  }
  getQuestionsByQuiz() {
    this.questionService.getQuestionsByQuiz(this.questionQuizId).subscribe(
      (questions: Question[]) => {
        this.questionList = questions
        this.loadAnswersForQuestions();
      },
      (error) => {
        console.error('Wystąpił błąd podczas pobierania pytań:', error);
      }
    );
  }

  getAnswerForm(index: number) {
    return (this.questionForm.get('answers') as FormArray).controls[
      index
      ] as FormGroup;
  }

  addQuestion() {
    if (this.questionForm.invalid) {
      return;
    }

    this.newQuestion.questionText = this.questionForm.value.questionText;
    this.newQuestion.questionType = this.questionForm.value.questionType;

    if (this.newQuestion.questionText && this.newQuestion.questionQuizId) {
      this.questionService.createQuestion(this.newQuestion).subscribe(
        (response: Question) => {
          console.log('Pytanie zostało zapisane:', response);
          this.createAnswers(response.id);
          this.questionForm.reset();
          this.newQuestion = {
            id: '',
            questionText: '',
            questionType: 'single',
            questionQuizId: '',
            answers: [],
          };
          this.showCheckboxInfo = false;
        },
        (error) => {
          console.error('Wystąpił błąd podczas zapisywania pytania:', error);
        }
      );
    } else {
      console.log('Wypełnij wszystkie pola');
    }

  }

  createAnswers(questionId: number) {
    const answers = (this.questionForm.get('answers') as FormArray).value.map((answer) => ({
      ...answer,
      answerQuestionId: questionId,
    }));

    answers.forEach((answer) => {
      this.answerService.createAnswer(answer).subscribe(
        (response: Answer) => {
          console.log('Odpowiedź została zapisana:', response);
        },
        (error) => {
          console.error('Wystąpił błąd podczas zapisywania odpowiedzi:', error);
          console.error('Błąd szczegóły:', error.error);
        }
      );
    });
    window.location.reload();
  }

  addAnswer() {
    if ((this.questionForm.get('answers') as FormArray).length === 0) {
      this.showCheckboxInfo = true;
    }

    const newAnswerFormGroup = this.formBuilder.group({
      id: '',
      answerForTheQuestion: '',
      confirmedAnswer: false

    });

    (this.questionForm.get('answers') as FormArray).push(newAnswerFormGroup);
    this.newQuestion.answers.push(newAnswerFormGroup.value);
    this.answers.push(newAnswerFormGroup.value);
  }

  toggleCorrectAnswer(answerFormGroup: FormGroup) {
    const answerIndex = (this.questionForm.get('answers') as FormArray).controls.findIndex(
      (control) => control === answerFormGroup
    );

    const answerControl = this.getAnswerForm(answerIndex);
    const answer = this.newQuestion.answers[answerIndex];

    answerControl.get('confirmedAnswer').setValue(!answer.confirmedAnswer);
  }

  removeAnswer(answerFormGroup: FormGroup) {
    const answersArray = this.questionForm.get('answers') as FormArray;
    const answerIndex = answersArray.controls.findIndex((control) => control === answerFormGroup);

    if (answerIndex > -1) {
      answersArray.removeAt(answerIndex);
      this.newQuestion.answers.splice(answerIndex, 1);
      this.answers.splice(answerIndex, 1);
    }
  }

  updateQuestionType() {
    const questionType = this.questionForm.value.questionType;
    this.showCheckboxInfo = questionType === 'multiple';
  }
  // private question: any;

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

