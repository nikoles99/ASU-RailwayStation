import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationComponent} from '../registration/registration.component';
import {RouteSearchComponent} from '../route-search/route-search.component';
import {BookTicketsComponent} from '../book-tickets/book-tickets.component';


const routes: Routes = [
  {path: '', component: RouteSearchComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: 'bookTickets', component: BookTicketsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
