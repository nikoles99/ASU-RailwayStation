import {Component, OnInit} from '@angular/core';
import {User} from "../common/model/users";
import {RegistrationService} from "./service/registration.service";
import {Location}                 from '@angular/common';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers: [RegistrationService]
})
export class RegistrationComponent implements OnInit {
  user: User = new User();

  constructor(private registrationService: RegistrationService,
              private location: Location) {
  }

  public ngOnInit() {
  }

  public registration(user: User): void {
    if (this.validate(user)) {
      this.registrationService.registration(user).then(() => this.goBack());
    }
  }

  public goBack(): void {
    this.location.back();
  }

  private validate(user: User): boolean {
    user.clearErrors();
    var isValidate = true;
    if (user.login == null || user.login == "") {
      user.loginError = "Введите логин";
      isValidate = false;
    }
    if ((user.password == null || user.password == "") || (user.confirmPassword == null || user.confirmPassword == "")
      || (user.password != user.confirmPassword)) {
      user.passwordError = "Пароли не совпадают";
      user.confirmPasswordError = "Пароли не совпадают";
      isValidate = false;
    }
    if (user.name == null || user.name == "") {
      user.firstNameError = "Введите имя";
      isValidate = false;
    }
    if (user.lastName == null || user.lastName == "") {
      user.lastNameError = "Введите фамилию";
      isValidate = false;
    }
    if (user.email == null || user.email == "" || user.email.indexOf("@") == -1) {
      user.emailError = "Заполните поле email";
      isValidate = false;
    }
    return isValidate;
  }

}
