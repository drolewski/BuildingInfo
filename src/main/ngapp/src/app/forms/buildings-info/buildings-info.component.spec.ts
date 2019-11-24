import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuildingsInfoComponent } from './buildings-info.component';

describe('BuildingsInfoComponent', () => {
  let component: BuildingsInfoComponent;
  let fixture: ComponentFixture<BuildingsInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuildingsInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuildingsInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
