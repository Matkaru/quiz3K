import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Question} from "../pages/quiz-page/add-question-to-quiz/question.model";


@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private apiUrl = 'http://localhost:8080/api/questions';

  constructor(private http: HttpClient) {}

  getQuestionsByQuiz(quizId: string): Observable<Question[]> {
    const url = `${this.apiUrl}?quizId=${quizId}`;
    return this.http.get<Question[]>(url);
  }

  createQuestion(question: Question): Observable<Question> {
    return this.http.post<Question>(this.apiUrl, question);
  }

  updateQuestion(question: Question): Observable<Question> {
    const url = `${this.apiUrl}/${question.id}`;
    return this.http.put<Question>(url, question);
  }

  deleteQuestion(questionId: string): Observable<void> {
    const url = `${this.apiUrl}/${questionId}`;
    return this.http.delete<void>(url);
  }
}
