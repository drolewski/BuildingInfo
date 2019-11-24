import { Component, OnInit, Input, ViewChild, ViewChildren, QueryList } from '@angular/core';
import { Building } from 'src/app/models/building';
import { FormControl, FormGroup, FormArray } from '@angular/forms';
import { Level } from 'src/app/models/level';
import { LevelEditorComponent } from '../level-editor/level-editor.component';
import { BuildingsService } from 'src/app/services/buildings/buildings.service';

@Component({
  selector: 'app-building-editor',
  templateUrl: './building-editor.component.html',
  styleUrls: ['./building-editor.component.scss']
})
export class BuildingEditorComponent implements OnInit {

  constructor(private buildingsService: BuildingsService) { }

  @Input()
  building: Building;

  @ViewChildren('levelEditor')
  levelEditorComponents: QueryList<LevelEditorComponent>;

  isNew: boolean;

  nameForm = new FormControl('');
  levelsForm = new FormArray([]);
  form = new FormGroup({
    name: this.nameForm,
    levels: this.levelsForm
  });

  ngOnInit() {
    this.isNew = this.building == null;
    if (!this.building) {
      this.building = new Building();
    }
    this.fillForm();
  }

  onSubmit() {
    const building = this.createBuilding();
    if (this.isNew) {
      this.buildingsService.createBuilding(building).subscribe();
    } else {
      console.log(building);
      this.buildingsService.updateBuilding(building).subscribe();
    }
  }

  createBuilding(): Building {
    const building = new Building();
    building.id = this.building.id;
    building.name = this.nameForm.value;
    building.immoveables = this.levelEditorComponents.map(component => {
      return component.createLevel();
    });
    return building;
  }

  fillForm() {
    this.nameForm.setValue(this.building.name);
  }

  addLevel(level: Level) {
    this.building.immoveables.push(level);
  }

  deleteLevel(i: number) {
    this.building.immoveables.splice(i, 1);
  }
}
