import {Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Injectable} from "@angular/core";

@Injectable()
export class AbstractHttpService {

  constructor(protected http: Http) {
  }
  protected handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
