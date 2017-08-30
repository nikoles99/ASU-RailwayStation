import {Injectable} from "@angular/core";
import {User} from "../model/users";
import {Headers, Http} from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class LoginService {

  private authorizeUrl = 'http://localhost:8080/authentication';
  private logoutUrl = 'http://localhost:8080/logout';
  private isAuthorizedUrl = 'http://localhost:8080/isAuthenticated';

  constructor(private http: Http) {
  }

  login(user: User): void {
    this.http.post(this.authorizeUrl, user)
      .toPromise()
      .then(response => response.json().data as string)
      .catch(this.handleError);
  }

  logout(): void {
  }

  isAuthenticated(): User {
    return new User();
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
}
