import {Injectable} from "@angular/core";
import {User} from "../model/users";
import {Http, URLSearchParams, RequestOptions} from "@angular/http";
import "rxjs/add/operator/toPromise";

@Injectable()
export class LoginService {

  private authorizeUrl = 'http://localhost:8080/authentication';
  private logoutUrl = 'http://localhost:8080/logout';
  private isAuthorizedUrl = 'http://localhost:8080/isAuthenticated';

  constructor(private http: Http) {
  }

  public login(login: string, password: string): Promise<User> {
    const params = new URLSearchParams();
    params.set('login', login);
    params.set('password', password);
    const options = new RequestOptions({
      params: params,
      withCredentials: true,
    });
    var promise = this.http.post(this.authorizeUrl, {}, options)
      .toPromise()
      .catch(this.handleError);
    return promise.then(() => this.isAuthenticated());
  }

  public logout(): void {
    const options = new RequestOptions({
      withCredentials: true,
    });
    this.http.get(this.logoutUrl, options)
      .toPromise()
      .catch(this.handleError);
  }

  public isAuthenticated(): Promise<User> {
    const options = new RequestOptions({
      withCredentials: true,
    });
    return this.http.post(this.isAuthorizedUrl, {}, options)
      .toPromise()
      .then(response => response.json() as User)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
