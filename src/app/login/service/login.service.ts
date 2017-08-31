import {Injectable} from "@angular/core";
import {User} from "../model/users";
import {Headers, Http, URLSearchParams, RequestOptions, ResponseContentType} from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class LoginService {

  private authorizeUrl = 'http://localhost:8080/authentication';
  private logoutUrl = 'http://localhost:8080/logout';
  private isAuthorizedUrl = 'http://localhost:8080/isAuthenticated';

  constructor(private http: Http) {
  }

  public login(login: string, password: string): void {
    const params = new URLSearchParams();
    params.set('login', login);
    params.set('password', password);
    const options = new RequestOptions({
      params: params,
    });
    this.http.post(this.authorizeUrl, {}, options)
      .toPromise()
      .then(response => response.json().data as string)
      .catch(this.handleError);
  }

  public logout(): void {
  }

  public isAuthenticated(): User {
    return new User();
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }
}
