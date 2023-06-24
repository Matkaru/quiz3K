import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FirstPageComponent } from './pages/first-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import {LoginPageComponent} from "./pages/log-In-page/login-page.component";
import {QuizPageComponent} from "./pages/quiz-page/quiz-page.component";
import {
  AddQuestionToQuizComponent
} from "./pages/quiz-page/add-question-to-quiz/add-question-to-quiz.component";
import {ShareProjectComponent} from "./pages/share-project/share-project.component";
import {QuizStatisticsComponent} from "./pages/quiz-statistics/quiz-statistics.component";

const routes: Routes = [
  { path: '', component: FirstPageComponent },
  { path: 'rejestracja', component: RegistrationPageComponent },
  { path: 'logowanie', component: LoginPageComponent},
  { path: 'quiz', component: QuizPageComponent },
  { path: 'add-question-to-quiz/:quizId/:quizName', component: AddQuestionToQuizComponent },
  { path: 'api/share/quiz/:quizId/:quizName' , component: ShareProjectComponent},
  { path: 'quiz-statistics/:id/:quizName' , component: QuizStatisticsComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],

})
export class AppRoutingModule { }
