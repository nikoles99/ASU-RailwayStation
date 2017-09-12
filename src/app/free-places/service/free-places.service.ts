import {AbstractHttpService} from "../../common/service/abstract-http.service";
import {Train} from "../../common/model/train";
import {URLSearchParams} from "@angular/http";
import {Place} from "../../common/model/place";

export class PlaceService extends AbstractHttpService {

  private getFreePlacesByType = 'http://localhost:8080/getFreePlacesByType';

  public getFreePlaces(trainId: number, carriageType: string, departureDate: Date, arrivalDate: Date): Promise<Place[]> {
    const options = this.getPlacesOptions(trainId, carriageType, departureDate, arrivalDate);
    return super.doPost(this.getFreePlacesByType, {}, options) .then(response => response.json() as Place[]);
  }

  private getPlacesOptions(trainId: number, carriageType: string, departureDate: Date, arrivalDate: Date) {
    const params = new URLSearchParams();
    params.set('trainId', trainId + '');
    params.set('carriageType', carriageType);
    params.set('departureDate', JSON.stringify(departureDate.getTime()));
    params.set('arrivalDate', JSON.stringify(arrivalDate.getTime()));
    const options = super.getRequestOptions();
    options.params = params;
    return options;
  }
}
