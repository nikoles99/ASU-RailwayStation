import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoadStationComponent } from './load-station.component';

describe('LoadStationComponent', () => {
  let component: LoadStationComponent;
  let fixture: ComponentFixture<LoadStationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoadStationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoadStationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
