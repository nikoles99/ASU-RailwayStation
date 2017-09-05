import {User} from "../model/users";
import {URLSearchParams, RequestOptions} from "@angular/http";
import "rxjs/add/operator/toPromise";
import {AbstractHttpService} from "../../common/service/abstract-http.service";

export class LoginService extends AbstractHttpService{

  private authorizeUrl = 'http://localhost:8080/authentication';
  private logoutUrl = 'http://localhost:8080/logout';
  private isAuthorizedUrl = 'http://localhost:8080/isAuthenticated';

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

}
