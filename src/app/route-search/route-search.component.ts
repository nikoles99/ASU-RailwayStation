import {Component, OnInit, ViewChild} from '@angular/core';
import {RouteSearchService} from './service/route-searach.service';
import {RouteComponent} from '../routes/route.component';

@Component({
  selector: 'app-route-search',
  templateUrl: './route-search.component.html',
  styleUrls: ['./route-search.component.css'],
  providers: [RouteSearchService]
})
export class RouteSearchComponent implements OnInit {

  @ViewChild(RouteComponent) routeComponent;

  constructor() {
  }

  ngOnInit() {
  }

  public search(departureDate: Date, departureStation: string, arrivalDate: Date, arrivalStation: string): void {
    this.routeComponent.searchRoute(departureDate, departureStation, arrivalDate, arrivalStation);
  }

}
