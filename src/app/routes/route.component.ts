import {Train} from "../common/model/train";
import {Input, Component, OnInit, ViewChild} from "@angular/core";
import {RouteService} from "./service/route.service";
import {ActivatedRoute} from "@angular/router";
import {Schedule} from "../common/model/schedule";
import {Station} from "../common/model/station";
import {FreePlacesComponent} from "../free-places/free-places.component";

@Component({
  selector: 'app-routes',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css'],
  providers: [RouteService]
})
export class RouteComponent implements OnInit {

  @ViewChild(FreePlacesComponent) freePlacesComponent;

  @Input() trains: Train[];
  departureStation: Station;
  arrivalStation: Station;

  constructor(private routeService: RouteService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {

  }

  public  searchRoute(departureDate: Date, departureStation: Station, arrivalDate: Date, arrivalStation: Station) {
    this.departureStation = Object.assign({}, departureStation);
    this.arrivalStation = Object.assign({}, arrivalStation);
    ;
    return this.routeService.searchRoutes(departureDate, departureStation.name, arrivalDate, arrivalStation.name)
      .then(trains => this.trains = trains);
  }

  public getDepartureStationDate(schedules: Schedule[]): Date {
    let departureDate;
    schedules.forEach((schedule) => {
      if (schedule.stationId == this.departureStation.id) {
        departureDate = new Date(schedule.departureDate);
      }
    });
    return departureDate;
  }

  public getArrivalStationDate(schedules: Schedule[]): Date {
    let arrivalDate;
    schedules.forEach((schedule) => {
      if (schedule.stationId == this.arrivalStation.id) {
        arrivalDate = new Date(schedule.arrivalDate);
      }
    });
    return arrivalDate;
  }
}