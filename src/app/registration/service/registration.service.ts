import {User} from "../../common/model/users";
import {Headers, Http, RequestOptions} from '@angular/http';
import 'rxjs/add/operator/toPromise';
import {AbstractHttpService} from "../../common/service/abstract-http.service";

export class RegistrationService extends AbstractHttpService{

  private registrationUrl = 'http://localhost:8080/registration';

  public registration(user: User): Promise<any> {
    let headers = new Headers({'Content-Type': 'application/json'});
    const options = new RequestOptions({
      headers: headers,
    });
    return this.http.post(this.registrationUrl, user, options)
      .toPromise()
      .catch(this.handleError);
  }
}
