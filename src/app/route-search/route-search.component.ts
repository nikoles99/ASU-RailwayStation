import {Component, OnInit} from '@angular/core';
import {Station} from "../common/model/station";
import {RouteSearchService} from "./service/route-searach.service";
import {Observable, Subject} from "rxjs";

@Component({
  selector: 'app-route-search',
  templateUrl: './route-search.component.html',
  styleUrls: ['./route-search.component.css'],
  providers: [RouteSearchService]
})
export class RouteSearchComponent implements OnInit {
  selectedDepartureStation: Station = new Station();
  selectedArrivalStation: Station = new Station();
  departureStations: Observable<Station[]>;
  arrivalStations: Observable<Station[]>;
  searchDepartureTerms = new Subject<string>();
  searchArrivalTerms = new Subject<string>();

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

  public onSelectDepartureStation(station: Station) {
    this.searchDepartureStations('');
    this.selectedDepartureStation = station;
  }

  public onSelectArrivalStation(station: Station) {
    this.searchArrivalStations('');
    this.selectedArrivalStation = station;
  }

  public searchDepartureStations(term: string): void {
    this.searchDepartureTerms.next(term);
  }

  public searchArrivalStations(term: string): void {
    this.searchArrivalTerms.next(term);
  }
}
