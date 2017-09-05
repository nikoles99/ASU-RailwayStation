import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouteSearchComponent } from './route-search/route-search.component';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';
import {RegistrationComponent} from "./registration/registration.component";
import {AppRoutingModule} from "./routing/app-routing.module";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RouteSearchComponent,
    RegistrationComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
