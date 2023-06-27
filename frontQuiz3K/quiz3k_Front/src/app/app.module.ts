import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FirstPageComponent } from './pages/first-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginPageComponent} from "./pages/log-In-page/login-page.component";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {QuizPageComponent} from "./pages/quiz-page/quiz-page.component";
import { MatTableModule } from '@angular/material/table';
import {AuthInterceptorService} from "./pages/auth-page/auth-interceptor.service";
import {MatInputModule} from "@angular/material/input";
import { AddQuestionToQuizComponent } from './pages/quiz-page/add-question-to-quiz/add-question-to-quiz.component';
import {ShareProjectComponent} from "./pages/share-project/share-project.component";
import { QuizStatisticsComponent } from './pages/quiz-statistics/quiz-statistics.component';
import {AuthGuard} from "./pages/auth-page/auth.guard";


@NgModule({
  declarations: [
    AppComponent,
    FirstPageComponent,
    RegistrationPageComponent,
    LoginPageComponent,
    QuizPageComponent,
    AddQuestionToQuizComponent,
    ShareProjectComponent,
    QuizStatisticsComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatTableModule,
    MatInputModule,
  ],
  exports: [
    RegistrationPageComponent,
    QuizPageComponent,
    AddQuestionToQuizComponent,
    ShareProjectComponent
  ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true},AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
