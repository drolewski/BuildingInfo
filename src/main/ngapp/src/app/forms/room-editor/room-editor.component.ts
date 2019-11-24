import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Room } from 'src/app/models/room';
import { FormControl, FormGroup, FormArray } from '@angular/forms';

@Component({
  selector: 'app-room-editor',
  templateUrl: './room-editor.component.html',
  styleUrls: ['./room-editor.component.scss']
})
export class RoomEditorComponent implements OnInit {

  @Input()
  room: Room;

  @Output()
  accept: EventEmitter<Room> = new EventEmitter();

  @Output()
  delete: EventEmitter<void> = new EventEmitter();

  isNew: boolean;

  // Reactive forms
  nameForm = new FormControl('');
  surfaceForm = new FormControl('');
  cubatureForm = new FormControl('');
  heatingForm = new FormControl('');
  lightingForm = new FormControl('');
  form = new FormGroup({
    name: this.nameForm,
    surface: this.surfaceForm,
    cubature: this.cubatureForm,
    heating: this.heatingForm,
    lighting: this.lightingForm
  });

  constructor() { }

  ngOnInit() {
    this.fillForm();
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
  }

  onDelete() {
    this.delete.emit();
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
