import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Question} from "../pages/quiz-page/add-question-to-quiz/question.model";



@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private apiUrl = 'http://localhost:8080/api/questions';
  // private answerApiUrl = 'http://localhost:8080/api/answers';

  constructor(private http: HttpClient) {}

  getQuestionsByQuiz(questionQuizId: number): Observable<Question[]> {
    const url = `${this.apiUrl}?questionQuizId=${questionQuizId}`;
    return this.http.get<Question[]>(url);
  }

 createQuestion(question: { questionQuizId: string; id: string; questionType: string; questionText: string }): Observable<Question> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Question>(this.apiUrl, question, httpOptions);
  }

  // createAnswers(answers: any[]): Observable<any> {
  //   const httpOptions = {
  //     headers: new HttpHeaders({
  //       'Content-Type': 'application/json'
  //     })
  //   };
  //   return this.http.post<any>(this.answerApiUrl, answers, httpOptions);
  // }

  updateQuestion(question: Question): Observable<Question> {
    const url = `${this.apiUrl}/${question.id}`;
    return this.http.put<Question>(url, question);
  }

  deleteQuestion(questionId: string): Observable<void> {
    const url = `${this.apiUrl}/${questionId}`;
    return this.http.delete<void>(url);
  }
}
