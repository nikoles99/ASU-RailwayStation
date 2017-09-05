import {AbstractHttpService} from "../../common/service/abstract-http.service";
import {Station} from "../../common/model/station";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/map';


export class RouteSearchService extends AbstractHttpService {

  private getStationsUrl = 'http://localhost:8080/getAllStations';

  public getStations(term: string): Observable<Station[]> {
    let options = super.getRequestOptions;
    return this.http.post(this.getStationsUrl, {}, options)
      .map(response => response.json() as Station[]);
  }
}
