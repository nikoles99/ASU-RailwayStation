import {Injectable} from '@angular/core';
import {Ticket} from './model/ticket';
import {Place} from '../../common/model/place';
import {URLSearchParams} from '@angular/http';
import {AbstractHttpService} from '../../common/service/abstract-http.service';

export class BookService extends AbstractHttpService {

  private bookTicket = 'http://localhost:8080/bookTicket';
  private getFreePlacesByType = 'http://localhost:8080/getFreePlacesByType';

  public book(ticket: Ticket): Promise<any> {
    return super.doPost(this.getFreePlacesByType, ticket, null);
  }

  public getFreePlaces(trainId: number, carriageType: string, departureDate: Date, arrivalDate: Date): Promise<Place[]> {
    const options = this.getPlacesOptions(trainId, carriageType, departureDate, arrivalDate);
    return super.doPost(this.bookTicket, {}, options).then(response => response.json() as Place[]);
  }

  private getPlacesOptions(trainId: number, carriageType: string, departureDate: Date, arrivalDate: Date) {
    const params = new URLSearchParams();
    params.set('trainId', trainId + '');
    params.set('carriageType', carriageType);
    params.set('departureDate', JSON.stringify(departureDate.getTime()));
    params.set('arrivalDate', JSON.stringify(arrivalDate.getTime()));
    const options = super.getRequestOptions();
    options.params = params;
    return options;
  }

}
