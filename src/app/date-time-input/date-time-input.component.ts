import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-date-time-input',
  templateUrl: './date-time-input.component.html',
  styleUrls: ['./date-time-input.component.css']
})
export class DateTimeInputComponent implements OnInit {

  dateTime = new Date();

  constructor() {
  }

  ngOnInit() {
  }

  public onDateChange(date: string) {
    let template = this.dateTime.toLocaleString();
    template = this.setDate(date, template);
    this.dateTime = new Date(template);
  }

  private setDate(date: string, template: string) {
    let space = template.indexOf(' ');
    if (date.indexOf(':') !== -1) {
      template = template.replace(template.substring(++space, template.length), date);
    } else {
      template = template.replace(template.substring(0, --space), date);
    }
    return template;
  }

}
