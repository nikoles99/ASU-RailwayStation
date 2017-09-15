import {AbstractHttpService} from '../../common/service/abstract-http.service';
import {URLSearchParams} from '@angular/http';
import {Place} from '../../common/model/place';
import {FreePlaces} from '../model/free-places';

export class PlaceService extends AbstractHttpService {

  private getFreePlacesByType = 'http://localhost:8080/getFreePlacesByType';
  private getFreePlacesSizeUrl = 'http://localhost:8080/getFreePlacesSize';

  public getFreePlacesSize(trainId: number, departureDate: Date, arrivalDate: Date): Promise<FreePlaces> {
    const options = this.getPlacesOptions(trainId, departureDate, arrivalDate);
    return super.doPost(this.getFreePlacesSizeUrl, {}, options).then(response => response.json() as FreePlaces);
  }

  public getFreePlaces(trainId: number, carriageType: string, departureDate: Date, arrivalDate: Date): Promise<Place[]> {
    const options = this.getPlacesOptions(trainId, departureDate, arrivalDate);
    return super.doPost(this.getFreePlacesByType, {}, options).then(response => response.json() as Place[]);
  }

  private getPlacesOptions(trainId: number, departureDate: Date, arrivalDate: Date) {
    const params = new URLSearchParams();
    params.set('trainId', trainId + '');
    params.set('departureDate', JSON.stringify(departureDate.getTime()));
    params.set('arrivalDate', JSON.stringify(arrivalDate.getTime()));
    const options = super.getRequestOptions();
    options.params = params;
    return options;
  }
}
