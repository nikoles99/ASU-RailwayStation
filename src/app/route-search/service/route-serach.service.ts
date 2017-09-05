import {Injectable} from "@angular/core";
import {User} from "../../login/model/users";
import {Headers, Http, RequestOptions} from '@angular/http';
import 'rxjs/add/operator/toPromise';

export class RouteSearchService {
  private registrationUrl = 'http://localhost:8080/registration';

  constructor(private http: Http) {
  }

  public registration(user: User): Promise<any> {
    let headers = new Headers({'Content-Type': 'application/json'});
    const options = new RequestOptions({
      headers: headers,
    });
    return this.http.post(this.registrationUrl, user, options)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
