import { Component, OnInit, Input, ViewChildren, QueryList, Output, EventEmitter } from '@angular/core';
import { FormArray, FormControl, FormGroup } from '@angular/forms';
import { Level } from 'src/app/models/level';
import { Room } from 'src/app/models/room';
import { RoomEditorComponent } from '../room-editor/room-editor.component';
import { BuildingsService } from 'src/app/services/buildings/buildings.service';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-level-editor',
  templateUrl: './level-editor.component.html',
  styleUrls: ['./level-editor.component.scss']
})
export class LevelEditorComponent implements OnInit {

  constructor(private snackBar: MatSnackBar) { }

  @Input()
  level: Level;

  @Input()
  parentFormArray: FormArray;

  @Output()
  accept: EventEmitter<Level> = new EventEmitter();

  @Output()
  delete: EventEmitter<void> = new EventEmitter();

  @ViewChildren('roomEditor')
  roomEditorcomponents: QueryList<RoomEditorComponent>;

  isNew: boolean;

  nameForm = new FormControl('');
  roomsForm = new FormArray([]);
  form = new FormGroup({
    name: this.nameForm,
    levels: this.roomsForm
  });


  ngOnInit() {
    this.isNew = this.level == null;
    if (!this.level) {
      this.level = new Level();
    }
    this.fillForm();
    this.parentFormArray.push(this.form);
  }

  fillForm() {
    this.nameForm.setValue(this.level.name);
  }

  onSubmit() {
    const level = this.createLevel();
    this.accept.emit(level);
    this.snackBar.open(level.name + ' has been successfully created!', 'Close', {
      duration: 2000,
    });
  }

  onDelete() {
    this.delete.emit();
    this.snackBar.open(this.level.name + ' has been deleted!', 'Close', {
      duration: 2000,
    });
  }

  createLevel() {
    const level = new Level();
    level.name = this.nameForm.value;
    level.immoveables = this.roomEditorcomponents.map((component) => {
      return component.createRoom();
    });
    return level;
  }

  addRoom(room: Room) {
    this.level.immoveables.push(room);
  }

  deleteRoom(i: number) {
    this.level.immoveables.splice(i, 1);
  }

}
