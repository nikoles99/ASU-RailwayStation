import {Component, OnInit, ViewChild} from '@angular/core';
import {Station} from '../common/model/station';
import {RouteSearchService} from './service/route-searach.service';
import {Observable, Subject} from 'rxjs';
import {RouteComponent} from '../routes/route.component';

@Component({
  selector: 'app-route-search',
  templateUrl: './route-search.component.html',
  styleUrls: ['./route-search.component.css'],
  providers: [RouteSearchService]
})
export class RouteSearchComponent implements OnInit {

  @ViewChild(RouteComponent) routeComponent;

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

  public onDepartureDateChange(date: string) {
    let template = this.departureDate.toLocaleString();
    template = this.setDate(date, template);
    this.departureDate = new Date(template);
  }

  private setDate(date: string, template: string) {
    let space = template.indexOf(' ');
    if (date.indexOf(':') !== -1) {
      template = template.replace(template.substring(++space, template.length), date);
    } else {
      template = template.replace(template.substring(0, --space), date);
    }
    return template;
  }

  public onArrivalDateChange(date: string) {
    let template = this.arrivalDate.toLocaleString();
    template = this.setDate(date, template);
    this.arrivalDate = new Date(template);
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
    this.routeComponent.searchRoute(this.departureDate, this.selectedDepartureStation, this.arrivalDate, this.selectedArrivalStation);

  }

}
