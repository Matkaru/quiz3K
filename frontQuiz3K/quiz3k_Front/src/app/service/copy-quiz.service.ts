import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CopyQuizService {
  private apiUrl = 'http://localhost:8080/api/share/quiz/{id}';

  constructor(private http: HttpClient) { }

  saveQuiz(quizData: any): Observable<any> {
    return this.http.post(this.apiUrl , quizData);
  }
}
