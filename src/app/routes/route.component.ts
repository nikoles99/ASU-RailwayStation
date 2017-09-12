import {Train} from "../common/model/train";
import {Input, Component, OnInit} from "@angular/core";
import {RouteService} from "./service/route.service";
import {ActivatedRoute, ParamMap} from "@angular/router";

@Component({
  selector: 'app-routes',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css'],
  providers: [RouteService]
})
export class RouteComponent implements OnInit {

  @Input() trains: Train[];

  constructor(private routeService: RouteService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    /*  this.route.paramMap
     .switchMap((params: ParamMap) => this.searchRoutes(params))
     .subscribe(trains => this.trains = trains);*/
  }

  public  searchRoute(departureDate: Date, departureStation: string, arrivalDate: Date, arrivalStation: string) {
    return this.routeService.searchRoutes(departureDate, departureStation, arrivalDate, arrivalStation)
      .then(trains => this.trains = trains);
  }

  /*  private searchRoutes(params: ParamMap) {
   const departureDate = params.get('departureDate');
   const departureStation = params.get('departureStation');
   const arrivalDate = params.get('arrivalDate');
   const arrivalStation = params.get('arrivalStation');
   return this.routeService.searchRoutes(new Date(departureDate), departureStation, new Date(arrivalDate), arrivalStation);
   }*/

}
