import {Component, OnInit} from '@angular/core';
import {LoginService} from "./service/login.service";
import {User} from "./model/users";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {
  login: string = '';
  password: string = '';
  authorizedUser: User;

  constructor(private loginService: LoginService) {
  }

  public ngOnInit() {
    console.log(this.login=="");
  }

  public authenticate(login: string, password: string) {
    this.loginService.login(login, password);
    this.login = null;
    this.password = null;
    this.authorizedUser = this.loginService.isAuthenticated();
  }

  public logout() {
    this.loginService.logout();
    this.login = '';
    this.password = '';
    this.authorizedUser = null;
  }
}
