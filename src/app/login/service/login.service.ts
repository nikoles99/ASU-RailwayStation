import {Injectable} from "@angular/core";
import {User} from "../model/users";

@Injectable()
export class LoginService {

  login(user: User): User {
    return user;
  }

  isAuthenticated():User{
    return new User();
  }
}
