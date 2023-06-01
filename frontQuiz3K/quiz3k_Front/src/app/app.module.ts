import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FirstPageComponent } from './pages/first-page.component';
import { AppComponent } from './app.component';

@NgModule({
  declarations: [
    AppComponent,
    FirstPageComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
