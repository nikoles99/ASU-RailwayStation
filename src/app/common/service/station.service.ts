import {AbstractHttpService} from './abstract-http.service';
import {Station} from '../model/station';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import {URLSearchParams} from '@angular/http';


export class StationService extends AbstractHttpService {

  private getStationsUrl = 'http://localhost:8080/getStationByName';
  private deleteStation = 'http://localhost:8080/deleteStation';
  private addStation = 'http://localhost:8080/addStation';

  public getStations(stationName: string): Observable<Station[]> {
    const options = super.getRequestOptions();
    const params = new URLSearchParams();
    params.set('name', stationName);
    options.params = params;
    return this.http.post(this.getStationsUrl, {}, options)
      .map(response => response.json() as Station[]);
  }

  public add(stationName: string): Promise<void> {
    return super.doPost(this.addStation, {name: stationName}, null);
  }

  public remove(stationName: string): Promise<void> {
    return super.doPost(this.deleteStation, {name: stationName}, null);
  }

}
