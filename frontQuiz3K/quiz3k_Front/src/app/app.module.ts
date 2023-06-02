import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { FirstPageComponent } from './pages/first-page.component';
import { RegistrationPageComponent } from './pages/registration-page/registration-page.component';
import {FormsModule} from "@angular/forms";
import {LoginPageComponent} from "./pages/log-In-page/login-page.component";

@NgModule({
  declarations: [
    AppComponent,
    FirstPageComponent,
    RegistrationPageComponent,
    LoginPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  exports: [
    RegistrationPageComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
