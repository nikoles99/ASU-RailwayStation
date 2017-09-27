import {Component, OnInit} from '@angular/core';
import {LoginService} from './service/login.service';
import {User} from '../common/model/users';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [LoginService]
})
export class LoginComponent implements OnInit {
  login = '';
  password = '';
  authorizedUser: User;

  constructor(private loginService: LoginService, private router: Router) {
  }

  public ngOnInit() {
    this.loginService.isAuthenticated()
      .then(authorizedUser => this.setAuthorizedUser(authorizedUser));
  }

  public authenticate(login: string, password: string) {
    this.loginService.login(login, password)
      .then(authorizedUser => this.setAuthorizedUser(authorizedUser));
  }

  public logout() {
    this.loginService.logout();
    this.login = '';
    this.password = '';
    this.authorizedUser = null;
  }

  public redirectToCabinet() {
    let link;
    if (this.authorizedUser.login === 'admin') {
      link = ['/administration'];
    } else {
      link = ['/cabinet'];
    }
    this.router.navigate(link);
  }

  private setAuthorizedUser(user: User) {
    if (user) {
      this.authorizedUser = user;
      this.password = null;
      this.login = null;
    }
  }
}
