import "rxjs/add/operator/toPromise";
import {AbstractHttpService} from "../../common/service/abstract-http.service";

export class RouteSearchService extends AbstractHttpService{
  private getStationsUrl = 'http://localhost:8080/getAllStations';

  public getStations(): Promise<any> {
    return super.doPost(this.getStationsUrl, {}, null);
  }
}
