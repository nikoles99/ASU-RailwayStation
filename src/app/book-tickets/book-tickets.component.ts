import {Component, OnInit} from '@angular/core';
import {Place} from '../common/model/place';
import {BookService} from './service/book.service';
import {User} from '../common/model/users';
import {LoginService} from '../login/service/login.service';
import {Ticket} from './service/model/ticket';
import 'rxjs/add/operator/switchMap';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-book-tickets',
  templateUrl: './book-tickets.component.html',
  styleUrls: ['./book-tickets.component.css'],
  providers: [BookService, LoginService]
})
export class BookTicketsComponent implements OnInit {

  trainId: number;
  trainName: string;
  carriageType: string;
  placeMap = new Map<number, Array<Place>>();
  user: User;
  ticket = new Ticket();
  choosePlaces = new Array<Place>();

  constructor(private bookService: BookService,
              private loginService: LoginService,
              private location: Location,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap
      .switchMap((params: ParamMap) => this.getFreePlaces(params))
      .subscribe(places => this.setPlaces(places));
    this.loginService.isAuthenticated()
      .then(authorizedUser => this.user = authorizedUser);
  }

  public goBack(): void {
    this.location.back();
  }

  public choosePlace(place: Place): void {
    const number = this.choosePlaces.indexOf(place);
    if (number !== -1) {
      this.choosePlaces.splice(number, 1);
    } else {
      this.choosePlaces.push(place);
    }
  }

  private setPlaces(places: Place[]) {
    for (const place of places) {
      const carriageId = place.carriageId;
      const hasCarriage = this.placeMap.has(carriageId);
      if (!hasCarriage) {
        this.placeMap.set(carriageId, []);
      } else {
        const freePlaces = this.placeMap.get(carriageId);
        freePlaces.push(place);
      }
    }
  }

  private getFreePlaces(params: ParamMap): Promise<Place[]> {
    this.ticket.departureDate = new Date(+params.get('departureDate'));
    this.ticket.arrivalDate = new Date(+params.get('arrivalDate'));
    this.carriageType = params.get('carriageType');
    this.ticket.departureStation = params.get('departureStation');
    this.ticket.arrivalStation = params.get('arrivalStation');
    this.trainId = +params.get('trainId');
    this.trainName = params.get('trainName');
    return this.bookService.getFreePlaces(this.trainId, this.carriageType, this.ticket.departureDate, this.ticket.arrivalDate);
  }

  public book(): void {
    for (const place of this.choosePlaces) {
      const ticket = Object.assign({}, this.ticket);
      ticket.placeId = place.id;
      ticket.placeNumber = place.number;
      this.bookService.book(ticket);
    }
  }

}
