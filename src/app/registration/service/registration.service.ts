import {Injectable} from "@angular/core";
import {User} from "../../login/model/users";
import {Http, RequestOptions} from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class RegistrationService {

  private registrationUrl = 'http://localhost:8080/registration';

  constructor(private http: Http) {
  }

  public registration(user: User): void{
    const options = new RequestOptions({
      withCredentials: true,
    });
    this.http.post(this.registrationUrl, user, options)
      .toPromise()
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    return Promise.reject(error.message || error);
  }
}
