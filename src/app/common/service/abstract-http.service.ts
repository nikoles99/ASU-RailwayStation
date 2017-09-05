import {Headers, Http, RequestOptions, RequestOptionsArgs} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {Injectable} from "@angular/core";

@Injectable()
export class AbstractHttpService {

  constructor(protected http: Http) {
  }

  protected handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }

  protected doPost(url: string, body: any, options?: RequestOptionsArgs): Promise<any> {
    if (options == null) {
      options = this.getRequestOptions();
    }
    return this.http.post(url, body, options)
      .toPromise()
      .catch(this.handleError);

  }

  protected doGet(url: string, options?: RequestOptionsArgs): Promise<any> {
    if (options == null) {
      options = this.getRequestOptions();
    }
    return this.http.get(url, options)
      .toPromise()
      .catch(this.handleError);
  }

  protected getRequestOptions() {
    let headers = new Headers({'Content-Type': 'application/json'});
    return new RequestOptions({
      headers: headers,
      withCredentials: true
    });
  }
}
