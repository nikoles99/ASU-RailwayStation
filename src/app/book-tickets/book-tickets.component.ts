import {Component, OnInit} from '@angular/core';
import {Place} from '../common/model/place';
import {BookService} from './service/book.service';
import {Train} from '../common/model/train';
import {User} from '../common/model/users';
import {LoginService} from '../login/service/login.service';
import {Ticket} from './service/model/ticket';

@Component({
  selector: 'app-book-tickets',
  templateUrl: './book-tickets.component.html',
  styleUrls: ['./book-tickets.component.css'],
  providers: [BookService]
})
export class BookTicketsComponent implements OnInit {

  train: Train;
  departureDate: Date;
  arrivalDate: Date;
  freePlaces: Place[];
  carriageType: string;
  user: User;
  ticket: Ticket;

  constructor(private bookService: BookService, private loginSevice: LoginService) {
  }

  ngOnInit() {
  }

  public book(): void {
    this.bookService.book(this.ticket);
  }

}
