import {Component, Input, OnInit} from '@angular/core';
import {PlaceService} from './service/free-places.service';
import {FreePlaces} from './model/free-places';
import {Train} from '../common/model/train';

@Component({
  selector: 'app-free-places',
  templateUrl: './free-places.component.html',
  styleUrls: ['./free-places.component.css'],
  providers: [PlaceService]
})
export class FreePlacesComponent implements OnInit {

  @Input() train: Train;
  @Input() departureDate: Date;
  @Input() arrivalDate: Date;
  freePlaces: FreePlaces;

  constructor(private placesService: PlaceService) {
  }

  ngOnInit(): void {
    this.placesService.getFreePlacesSize(this.train.id, this.departureDate, this.arrivalDate)
      .then(freePlaces => this.freePlaces = freePlaces);
  }

}
