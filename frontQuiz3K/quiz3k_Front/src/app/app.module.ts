import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FirstPageComponent } from './pages/first-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {LoginPageComponent} from "./pages/log-In-page/login-page.component";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    FirstPageComponent,
    RegistrationPageComponent,
    LoginPageComponent
  ],
  imports: [
    BrowserModule, //dostep do przegladarki?
    AppRoutingModule, //routing
    HttpClientModule, //klientHttp
    ReactiveFormsModule,  //formularze,
    FormsModule
  ],
  exports: [
    RegistrationPageComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
