import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Quiz} from "../pages/quiz-page/quiz.model";
import {AuthService} from "../pages/auth-page/auth.service";

@Injectable({
  providedIn: 'root'
})
export class QuizService {
  private apiUrl = 'http://localhost:8080/api/quiz';

  constructor(private http: HttpClient) {
  }

  createQuiz(quiz: Quiz): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}`, quiz);
  }

  getAllQuiz(): Observable<Quiz[]> {
    return this.http.get<Quiz[]>(`${this.apiUrl}`);
  }

  deleteQuiz(quizId: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.apiUrl}/${quizId}`);
  }
  updateQuiz(quiz: Quiz): Observable<any> {
    const url = `${this.apiUrl}/${quiz.id}`; // URL do zaktualizowania konkretnego quizu
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(url, quiz, { headers: headers });
  }

  getQuizName(quizId: string): Observable<{ name: string }> {
    const url = `${this.apiUrl}/api/quiz/${quizId}`;
    return this.http.get<{ name: string }>(url);
  }
}
