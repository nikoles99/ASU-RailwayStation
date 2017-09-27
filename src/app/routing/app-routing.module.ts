import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationComponent} from '../registration/registration.component';
import {RouteSearchComponent} from '../route-search/route-search.component';
import {BookTicketsComponent} from '../book-tickets/book-tickets.component';
import {CabinetComponent} from '../cabinet/cabinet.component';
import {AdministrationComponent} from '../administration/administration.component';


const routes: Routes = [
  {
    path: '',
    component: RouteSearchComponent
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'bookTickets/:trainId/:trainName/:departureStation/:departureDate/:arrivalStation/:arrivalDate/:carriageType',
    component: BookTicketsComponent
  },
  {
    path: 'administration',
    component: AdministrationComponent
  },
  {
    path: 'cabinet',
    component: CabinetComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
