import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FirstPageComponent } from './pages/first-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import {LoginPageComponent} from "./pages/log-In-page/login-page.component";
import {QuizPageComponent} from "./pages/quiz-page/quiz-page.component";

const routes: Routes = [
  { path: '', component: FirstPageComponent },
  { path: 'rejestracja', component: RegistrationPageComponent },
  { path: 'logowanie', component: LoginPageComponent},
  { path: 'quizy', component: QuizPageComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],

})
export class AppRoutingModule { }
