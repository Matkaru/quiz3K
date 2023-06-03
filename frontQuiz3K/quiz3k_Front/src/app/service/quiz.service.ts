import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private apiUrl = 'http://localhost:8080/api/quiz';

  constructor(private http: HttpClient) { }

  getAllQuiz(): Observable<string[]> {
    return this.http.get<string[]>(`${this.apiUrl}`);
  }
}
