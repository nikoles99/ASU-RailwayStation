import {Input, Component, OnInit} from "@angular/core";
import {Place} from "../common/model/place";
import {PlaceService} from "./service/free-places.service";

@Component({
  selector: 'app-free-places',
  templateUrl: './free-places.component.html',
  styleUrls: ['./free-places.component.css'],
  providers: [PlaceService]
})
export class FreePlacesComponent implements OnInit {

  @Input() trainId: number;
  @Input() departureDate: Date;
  @Input() arrivalDate: Date;
  seats: Place[];
  coupe: Place[];
  common: Place[];
  reservedSeat: Place[];

  constructor(private placesService: PlaceService) {
  }

  ngOnInit(): void {
    this.placesService
      .getFreePlaces(this.trainId, 'SEAT_PLACE', this.departureDate, this.arrivalDate).then(places => this.seats = places);
    this.placesService
      .getFreePlaces(this.trainId, 'COUP', this.departureDate, this.arrivalDate).then(places => this.coupe = places);
    this.placesService
      .getFreePlaces(this.trainId, 'COMMON', this.departureDate, this.arrivalDate).then(places => this.common = places);
    this.placesService
      .getFreePlaces(this.trainId, 'RESERVED_SEAT', this.departureDate, this.arrivalDate).then(places => this.reservedSeat = places);

  }

}
