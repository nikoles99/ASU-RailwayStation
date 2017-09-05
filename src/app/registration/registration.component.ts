import {Component, OnInit} from '@angular/core';
import {User} from "../login/model/users";
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
      this.registrationService.registration(user);
    }
  }

  public goBack(): void {
    this.location.back();
  }

  private validate(user: User): boolean {
    if (user.login == null) {
      return false;
    }
  }

}
