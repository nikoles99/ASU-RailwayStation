import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LoginComponent} from './login/login.component';
import {RouteSearchComponent} from './route-search/route-search.component';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RegistrationComponent} from './registration/registration.component';
import {AppRoutingModule} from './routing/app-routing.module';
import {DatePipe} from '@angular/common';
import {RouteComponent} from './routes/route.component';
import {FreePlacesComponent} from './free-places/free-places.component';
import {BookTicketsComponent} from './book-tickets/book-tickets.component';
import { CabinetComponent } from './cabinet/cabinet.component';
import { AdministrationComponent } from './administration/administration.component';
import { LoadStationComponent } from './load-station/load-station.component';
import { DateTimeInputComponent } from './date-time-input/date-time-input.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RouteSearchComponent,
    RegistrationComponent,
    RouteComponent,
    FreePlacesComponent,
    BookTicketsComponent,
    CabinetComponent,
    AdministrationComponent,
    LoadStationComponent,
    DateTimeInputComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
