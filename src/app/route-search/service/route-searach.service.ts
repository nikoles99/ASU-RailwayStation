import {AbstractHttpService} from "../../common/service/abstract-http.service";
import {Station} from "../../common/model/station";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";
import {URLSearchParams} from "@angular/http";
import {Train} from "../../common/model/train";


export class RouteSearchService extends AbstractHttpService {

  private getStationsUrl = 'http://localhost:8080/getStationByName';
  private getTrainsByParams = "http://localhost:8080/getTrainsByParams";


  public getStations(term: string): Observable<Station[]> {
    let options = super.getRequestOptions();
    const params = new URLSearchParams();
    params.set('name', term);
    options.params = params;
    return this.http.post(this.getStationsUrl, {}, options)
      .map(response => response.json() as Station[]);
  }

  public searchRoutes(departureDate: Date, departureStation: string, arrivalDate: Date, arrivalStation: string): Promise<Train[]> {
    let options = this.getRouteOptions(departureStation, arrivalStation, departureDate, arrivalDate);
    return super.doPost(this.getTrainsByParams, {}, options);
  }

  private getRouteOptions(departureStation: string, arrivalStation: string, departureDate: Date, arrivalDate: Date) {
    const params = new URLSearchParams();
    params.set('departureStation', departureStation);
    params.set('arrivalStation', arrivalStation);
    params.set('departureDate', departureDate.toLocaleString());
    params.set('arrivalDate', arrivalDate.toLocaleString());
    const options = super.getRequestOptions();
    options.params = params;
    return options;
  }
}
