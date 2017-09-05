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

  allStations: Observable<Station[]>;
  private searchTerms = new Subject<string>();

  constructor(private routeSearchService: RouteSearchService) {
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit() {
    this.allStations = this.searchTerms
      .debounceTime(300)
      .distinctUntilChanged()
      .switchMap(term => term ? this.routeSearchService.getStations() : Observable.of<Station[]>([]))
      .catch(error => {
        console.log(error);
        return Observable.of<Station[]>([]);
      });
  }

}
