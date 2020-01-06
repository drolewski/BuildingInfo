import { Component, OnInit, Input, ViewChild, ViewChildren, QueryList } from '@angular/core';
import { Building } from 'src/app/models/building';
import { FormControl, FormGroup, FormArray, Validators } from '@angular/forms';
import { Level } from 'src/app/models/level';
import { LevelEditorComponent } from '../level-editor/level-editor.component';
import { BuildingsService } from 'src/app/services/buildings/buildings.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-building-editor',
  templateUrl: './building-editor.component.html',
  styleUrls: ['./building-editor.component.scss']
})
export class BuildingEditorComponent implements OnInit {

  constructor(private buildingsService: BuildingsService,
              private snackBar: MatSnackBar) { }

  @Input()
  building: Building;

  @ViewChildren('levelEditor')
  levelEditorComponents: QueryList<LevelEditorComponent>;

  isNew: boolean;

  nameForm = new FormControl('', Validators.required);
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
      this.snackBar.open(building.name + ' has been successfully created!', 'Close', {
        duration: 2000,
      });
    } else {
      console.log(building);
      this.buildingsService.updateBuilding(building).subscribe();
      this.snackBar.open(building.name + ' has been successfully updated!', 'Close', {
        duration: 2000,
      });
    }
  }

  onDelete() {
    this.buildingsService.deleteBuilding(this.building).subscribe();
    this.snackBar.open(this.building.name + ' has been deleted!', 'Close', {
      duration: 2000,
    });
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
