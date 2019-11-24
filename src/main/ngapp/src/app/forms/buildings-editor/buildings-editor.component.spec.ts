import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BuildingsEditorComponent } from './buildings-editor.component';

describe('BuildingsComponent', () => {
  let component: BuildingsEditorComponent;
  let fixture: ComponentFixture<BuildingsEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BuildingsEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BuildingsEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
