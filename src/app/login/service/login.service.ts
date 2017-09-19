import {User} from '../../common/model/users';
import {URLSearchParams, RequestOptions} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {AbstractHttpService} from '../../common/service/abstract-http.service';

export class LoginService extends AbstractHttpService {

  private authorizeUrl = 'http://localhost:8080/authentication';
  private logoutUrl = 'http://localhost:8080/logout';
  private isAuthorizedUrl = 'http://localhost:8080/isAuthenticated';

  public login(login: string, password: string): Promise<User> {
    const options = this.getLoginParams(login, password);
    const promise = super.doPost(this.authorizeUrl, {}, options);
    return promise.then(() => this.isAuthenticated());
  }

  public logout(): void {
    const options = new RequestOptions({
      withCredentials: true,
    });
    super.doGet(this.logoutUrl, options);
  }

  public isAuthenticated(): Promise<User> {
    return super.doPost(this.isAuthorizedUrl, {}, null)
      .then(response => response.json() as User);
  }

  private  getLoginParams(login: string, password: string) {
    const params = new URLSearchParams();
    params.set('login', login);
    params.set('password', password);
    const options = super.getRequestOptions();
    options.params = params;
    return options;
  }
}
