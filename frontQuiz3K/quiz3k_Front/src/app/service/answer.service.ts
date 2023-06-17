import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Answer, Question} from "../pages/quiz-page/add-question-to-quiz/question.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AnswerService {
  private answers: Answer[] = [];
  private apiUrl = 'http://localhost:8080/api/answers';

  constructor(private http: HttpClient) {}

  createAnswer(answer: Answer): Observable<Answer> {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    return this.http.post<Answer>(this.apiUrl, answer, httpOptions);
  }

  getAnswersByQuestion(answerQuestionId: number): Observable<Answer[]> {
    const url = `${this.apiUrl}?answerQuestionId=${answerQuestionId}`;
    return this.http.get<Answer[]>(url);
  }
}
