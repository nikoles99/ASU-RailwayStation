import {Component, OnInit} from '@angular/core';
import {Station} from '../common/model/station';
import {Train} from '../common/model/train';
import {TrainService} from '../common/service/train.service';
import {StationService} from '../common/service/station.service';

@Component({
  selector: 'app-administration',
  templateUrl: './administration.component.html',
  styleUrls: ['./administration.component.css'],
  providers: [TrainService, StationService]
})
export class AdministrationComponent implements OnInit {

  defaultCarriagesCount = 3;

  coupCarriagesCount = this.defaultCarriagesCount;
  seatPlacesCarriagesCount = this.defaultCarriagesCount;
  reservedPlacesCarriagesCount = this.defaultCarriagesCount;
  commonCarriagesCount = this.defaultCarriagesCount;

  train = new Train();

  route = new Map<Station, number>();


  constructor(private stationService: StationService, private trainService: TrainService) {
  }

  ngOnInit() {
  }

  private addStation(stationName: string) {
    this.stationService.add(stationName);
  }

  private removeStation(stationName: string) {
    this.stationService.remove(stationName);
  }

  private removeRoute(station: Station) {
    this.route.delete(station);
  }

  private setDistance(station: Station, distance: number) {
    this.route.delete(null);
    this.route.set(station, distance);
  }
}
