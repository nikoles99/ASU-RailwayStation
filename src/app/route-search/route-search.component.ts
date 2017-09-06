import {Component, OnInit} from '@angular/core';
import {Station} from "../common/model/station";
import {RouteSearchService} from "./service/route-searach.service";
import {Observable, Subject} from "rxjs";
import {Train} from "../common/model/train";

@Component({
  selector: 'app-route-search',
  templateUrl: './route-search.component.html',
  styleUrls: ['./route-search.component.css'],
  providers: [RouteSearchService]
})
export class RouteSearchComponent implements OnInit {
  departureStations: Observable<Station[]>;
  arrivalStations: Observable<Station[]>;
  searchDepartureTerms = new Subject<string>();
  searchArrivalTerms = new Subject<string>();

  arrivalDate = new Date();
  departureDate = new Date();
  selectedDepartureStation: Station = new Station();
  selectedArrivalStation: Station = new Station();

  constructor(private routeSearchService: RouteSearchService) {
  }


  ngOnInit() {
    this.departureStations = this.searchDepartureTerms
      .debounceTime(300)
      .distinctUntilChanged()
      .switchMap(term => term ? this.routeSearchService.getStations(term) : Observable.of<Station[]>([]))
      .catch(error => {
        console.log(error);
        return Observable.of<Station[]>([]);
      });
    this.arrivalStations = this.searchArrivalTerms
      .debounceTime(300)
      .distinctUntilChanged()
      .switchMap(term => term ? this.routeSearchService.getStations(term) : Observable.of<Station[]>([]))
      .catch(error => {
        console.log(error);
        return Observable.of<Station[]>([]);
      });
  }

  public  onDateChange(date: Date) {
    this.departureDate = date;
  }

  public onSelectDepartureStation(station: Station) {
    this.searchDepartureStations('');
    this.selectedDepartureStation = station;
  }

  public onSelectArrivalStation(station: Station) {
    this.searchArrivalStations('');
    this.selectedArrivalStation = station;
  }

  public searchDepartureStations(term: string): void {
    // this.selectedDepartureStation = null;
    this.searchDepartureTerms.next(term);
  }

  public searchArrivalStations(term: string): void {
    //  this.selectedArrivalStation = null;
    this.searchArrivalTerms.next(term);
  }

  public searchRoute(): void {
    if (this.selectedDepartureStation != null && this.selectedArrivalStation != null) {
      this.routeSearchService.searchRoutes(this.departureDate, this.selectedDepartureStation.name, this.arrivalDate, this.selectedArrivalStation.name)
        .then(trains => this.redirectToRoutes(trains));
    }
  }

  private redirectToRoutes(trains: Train[]) {

  }
}
