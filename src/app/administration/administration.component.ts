import {Component, OnInit} from '@angular/core';
import {DistanceStation} from "./model/distance-station";
import {Station} from "../common/model/station";

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.css'],
})
export class AdministrationComponent implements OnInit {

  defaultCarriagesCount = 3;

  coupCarriagesCount = this.defaultCarriagesCount;
  seatPlacesCarriagesCount = this.defaultCarriagesCount;
  reservedPlacesCarriagesCount = this.defaultCarriagesCount;
  commonCarriagesCount = this.defaultCarriagesCount;

  route = new Map<Station, number>();


  constructor() {
  }

  ngOnInit() {
  }

  private addRoute() {
    const maxRouteSize = 10;
    if (this.route.size < maxRouteSize) {
      this.route.set(null, null);
    }
  }

  private removeRoute(station: Station) {
    this.route.delete(station);
  }

  private setDistance(station: Station, distance: number) {
    this.route.delete(null);
    this.route.set(station, distance);
  }
}
