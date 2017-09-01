import {Component, OnInit} from '@angular/core';
import {User} from "../login/model/users";
import {RegistrationService} from "./service/registration.service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  providers: [RegistrationService]
})
export class RegistrationComponent implements OnInit {
  user: User;

  constructor(private registrationService: RegistrationService) {
  }

  public ngOnInit() {
  }

  private registrate(user: User): void {
    if (this.validate(user)) {
      this.registrationService.registration(user);
    }
  }

  private validate(user: User): boolean {
    if (user.login == null) {
      return false
    }
  }
}
