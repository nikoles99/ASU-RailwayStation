import {Component, OnInit} from '@angular/core';
import {Place} from '../common/model/place';
import {BookService} from './service/book.service';
import {Train} from '../common/model/train';
import {User} from '../common/model/users';
import {LoginService} from '../login/service/login.service';
import {Ticket} from './service/model/ticket';
import 'rxjs/add/operator/switchMap';
import {ActivatedRoute, ParamMap} from '@angular/router';

@Component({
  selector: 'app-book-tickets',
  templateUrl: './book-tickets.component.html',
  styleUrls: ['./book-tickets.component.css'],
  providers: [BookService, LoginService]
})
export class BookTicketsComponent implements OnInit {

  trainId: number;
  departureDate: Date;
  arrivalDate: Date;
  departureStation: string;
  arrivalStation: string;
  freePlaces: Place[];
  carriageType: string;
  user: User;
  ticket: Ticket;

  constructor(private bookService: BookService, private loginService: LoginService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.getFreePlaces(params))
      .subscribe(places => this.freePlaces = places);
    this.loginService.isAuthenticated()
      .then(authorizedUser => this.user = authorizedUser);
  }

  private getFreePlaces(params: ParamMap): Promise<Place[]> {
    this.trainId = +params.get('trainId');
    this.departureDate = new Date(params.get('departureDate'));
    this.arrivalDate = new Date(params.get('arrivalDate'));
    this.departureStation = params.get('departureStation');
    this.arrivalStation = params.get('arrivalStation');
    this.carriageType = params.get('carriageType');
    return this.bookService.getFreePlaces(this.trainId, this.carriageType, this.departureDate, this.arrivalDate);
  }

  public book(): void {
    this.bookService.book(this.ticket);
  }

}
