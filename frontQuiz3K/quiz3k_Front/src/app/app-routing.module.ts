import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FirstPageComponent } from './pages/first-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import {LoginPageComponent} from "./pages/log-In-page/login-page.component";
import {QuizPageComponent} from "./pages/quiz-page/quiz-page.component";
import {
  AddQuestionToQuizComponent
} from "./pages/quiz-page/add-question-to-quiz/add-question-to-quiz.component";

const routes: Routes = [
  { path: '', component: FirstPageComponent },
  { path: 'rejestracja', component: RegistrationPageComponent },
  { path: 'logowanie', component: LoginPageComponent},
  { path: 'quiz', component: QuizPageComponent },
  { path: 'add-question-to-quiz/:quizName', component: AddQuestionToQuizComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],

})
export class AppRoutingModule { }
