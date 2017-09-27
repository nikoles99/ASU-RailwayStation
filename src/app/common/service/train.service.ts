import {AbstractHttpService} from './abstract-http.service';
import {Train} from '../model/train';
import {URLSearchParams} from '@angular/http';

export class TrainService extends AbstractHttpService {

  private getByParams = 'http://localhost:8080/getTrainsByParams';
  private addTrain = 'http://localhost:8080/addTrains';

  public add(train: Train): Promise<void> {
    return super.doPost(this.addTrain, train, null);
  }

  public searchRoutes(departureDate: Date, departureStation: string, arrivalDate: Date, arrivalStation: string): Promise<Train[]> {
    const options = this.getRouteOptions(departureStation, arrivalStation, departureDate, arrivalDate);
    return super.doPost(this.getByParams, {}, options).then(response => response.json() as Train[]);
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
