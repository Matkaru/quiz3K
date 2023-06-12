import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Question} from "../pages/quiz-page/add-question-to-quiz/question.model";


@Injectable({
  providedIn: 'root'
})
export class QuestionService {
  private apiUrl = 'http://localhost:8080/api/questions'; // Zmień ten adres URL na odpowiedni dla twojego backendu

  constructor(private http: HttpClient) {}

  getQuestionsByQuiz(quizId: string): Observable<Question[]> {
    const url = `${this.apiUrl}?quizId=${quizId}`;
    return this.http.get<Question[]>(url);
  }
}
