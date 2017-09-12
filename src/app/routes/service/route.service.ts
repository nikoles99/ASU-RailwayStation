import {AbstractHttpService} from "../../common/service/abstract-http.service";
import {Train} from "../../common/model/train";
import {URLSearchParams} from "@angular/http";
import {Place} from "../../common/model/place";

export class RouteService extends AbstractHttpService {

  private getTrainsByParams = 'http://localhost:8080/getTrainsByParams';

  public searchRoutes(departureDate: Date, departureStation: string, arrivalDate: Date, arrivalStation: string): Promise<Train[]> {
    const options = this.getRouteOptions(departureStation, arrivalStation, departureDate, arrivalDate);
    return super.doPost(this.getTrainsByParams, {}, options) .then(response => response.json() as Train[]);
  }

  private getRouteOptions(departureStation: string, arrivalStation: string, departureDate: Date, arrivalDate: Date) {
    const params = new URLSearchParams();
    params.set('departureStation', departureStation);
    params.set('arrivalStation', arrivalStation);
    params.set('departureDate', JSON.stringify(departureDate.getTime()));
    params.set('arrivalDate', JSON.stringify(arrivalDate.getTime()));
    const options = super.getRequestOptions();
    options.params = params;
    return options;
  }
}
