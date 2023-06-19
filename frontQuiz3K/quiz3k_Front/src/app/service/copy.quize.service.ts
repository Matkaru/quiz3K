import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Quiz} from "../pages/quiz-page/quiz.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CopyQuizeService {
  private apiUrl = 'http://';

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
    const url = `${this.apiUrl}/${quiz.id}`;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put(url, quiz, { headers: headers });
  }


}
