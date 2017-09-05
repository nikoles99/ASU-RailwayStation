import {NgModule}             from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {RegistrationComponent} from "../registration/registration.component";
import {RouteSearchComponent} from "../route-search/route-search.component";


const routes: Routes = [
  {path: '', component: RouteSearchComponent},
  {path: 'registration', component: RegistrationComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
