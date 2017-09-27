import {Component, OnInit} from '@angular/core';
import {Station} from '../common/model/station';
import {Observable, Subject} from 'rxjs';
import {RouteSearchService} from '../route-search/service/route-searach.service';

@Component({
  selector: 'app-load-station',
  templateUrl: './load-station.component.html',
  styleUrls: ['./load-station.component.css'],
  providers: [RouteSearchService]
})
export class LoadStationComponent implements OnInit {
  searchTerms = new Subject<string>();
  stations: Observable<Station[]>;
  selectedStation: Station = new Station();

  constructor(private routeSearchService: RouteSearchService) {
  }

  ngOnInit() {
    this.stations = this.searchTerms
      .debounceTime(300)
      .distinctUntilChanged()
      .switchMap(term => term ? this.routeSearchService.getStations(term) : Observable.of<Station[]>([]))
      .catch(error => {
        console.log(error);
        return Observable.of<Station[]>([]);
      });
  }

  public onSelectStation(station: Station) {
    this.searchStations('');
    this.selectedStation = station;
  }

  public searchStations(term: string): void {
    this.searchTerms.next(term);
  }
}
