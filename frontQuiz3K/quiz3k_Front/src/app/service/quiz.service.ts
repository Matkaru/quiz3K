import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Quiz} from "../pages/quiz-page/quiz.model";

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private apiUrl = 'http://localhost:8080/api/quiz';

  constructor(private http: HttpClient) { }

  createQuiz(quiz: Quiz): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}`, quiz);
  }
  getAllQuiz(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`${this.apiUrl}`);
  }
}
