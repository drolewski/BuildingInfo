import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuildingEditorComponent } from './building-editor.component';

describe('BuildingEditorComponent', () => {
  let component: BuildingEditorComponent;
  let fixture: ComponentFixture<BuildingEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuildingEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuildingEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
