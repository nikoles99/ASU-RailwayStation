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
  user: User = new User;
  authorizedUser: User;

  constructor(private loginService: LoginService) {
  }

  ngOnInit() {
  }

  login(user: User) {
    this.loginService.login(user);
    this.authorizedUser = this.loginService.isAuthenticated();
    this.user = null;
  }

  logout() {
    this.loginService.logout();
    this.authorizedUser = null;
    this.user = new User();
  }
}
