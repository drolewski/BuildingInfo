import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { Room } from 'src/app/models/room';
import { FormControl, FormGroup, FormArray, Validators } from '@angular/forms';
import { MatSnackBar, MatExpansionPanel } from '@angular/material';

@Component({
  selector: 'app-room-editor',
  templateUrl: './room-editor.component.html',
  styleUrls: ['./room-editor.component.scss']
})
export class RoomEditorComponent implements OnInit {

  @Input()
  room: Room;

  @Input()
  parentFormArray: FormArray;

  @ViewChild('panel', {static: true})
  panel: MatExpansionPanel;

  @Output()
  accept: EventEmitter<Room> = new EventEmitter();

  @Output()
  delete: EventEmitter<void> = new EventEmitter();

  readonly numberRegex = '[+-]?([0-9]*[.])?[0-9]+';

  isNew: boolean;

  // Reactive forms
  nameForm = new FormControl('', [Validators.required]);
  surfaceForm = new FormControl('', [Validators.required, Validators.pattern(this.numberRegex)]);
  cubatureForm = new FormControl('',  [Validators.required, Validators.pattern(this.numberRegex)]);
  heatingForm = new FormControl('',  [Validators.required, Validators.pattern(this.numberRegex)]);
  lightingForm = new FormControl('',  [Validators.required, Validators.pattern(this.numberRegex)]);
  form = new FormGroup({
    name: this.nameForm,
    surface: this.surfaceForm,
    cubature: this.cubatureForm,
    heating: this.heatingForm,
    lighting: this.lightingForm
  });

  constructor(private snackBar: MatSnackBar) { }

  ngOnInit() {
    if (this.room) {
      this.fillForm();
      this.parentFormArray.push(this.form);
    }
  }

  fillForm() {
    if (this.room) {
      this.nameForm.setValue(this.room.name);
      this.surfaceForm.setValue(this.room.surface);
      this.cubatureForm.setValue(this.room.cubature);
      this.heatingForm.setValue(this.room.heating);
      this.lightingForm.setValue(this.room.lighting);
    }
  }

  onSubmit() {
    const room = this.createRoom();
    this.accept.emit(room);
    this.snackBar.open(room.name + ' has been successfully created!', 'Close', {
      duration: 2000,
    });
    this.form.reset();
    this.panel.close();
  }

  onDelete() {
    this.delete.emit();
    this.snackBar.open(this.room.name + ' has been deleted!', 'Close', {
      duration: 2000,
    });
  }

  createRoom(): Room {
    const room = new Room();
    room.name = this.nameForm.value;
    room.surface = this.surfaceForm.value;
    room.cubature = this.cubatureForm.value;
    room.heating = this.heatingForm.value;
    room.lighting = this.lightingForm.value;
    return room;
  }

}
