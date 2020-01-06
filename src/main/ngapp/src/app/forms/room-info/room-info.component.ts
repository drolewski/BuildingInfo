import { Component, OnInit, Input } from '@angular/core';
import { Room } from 'src/app/models/room';
import { TableElement } from 'src/app/models/table-element';
import { RoomService } from 'src/app/services/room/room.service';

@Component({
  selector: 'app-room-info',
  templateUrl: './room-info.component.html',
  styleUrls: ['./room-info.component.scss']
})
export class RoomInfoComponent implements OnInit {

  @Input()
  room: Room;

  data: TableElement[];
  displayedColumns: string[] = ['name', 'value'];

  constructor(private roomService: RoomService) { }

  ngOnInit() {
    this.initData();
  }

  initData() {
    this.data = [
      {name: 'Surface', value: this.roomService.getSurface(this.room)},
      {name: 'Cubature', value: this.roomService.getCubature(this.room)},
      {name: 'Lighting', value: this.roomService.getLighting(this.room)},
      {name: 'Heating', value: this.roomService.getHeating(this.room)}
    ];
  }

}
