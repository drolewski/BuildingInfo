import { Component, OnInit, Input } from '@angular/core';
import { Room } from 'src/app/models/room';
import { TableElement } from 'src/app/models/table-element';
import { RoomService } from 'src/app/services/room/room.service';
import { first, map } from 'rxjs/operators';
import { Observable, forkJoin } from 'rxjs';
import { Filter } from 'src/app/models/filter';

@Component({
  selector: 'app-room-info',
  templateUrl: './room-info.component.html',
  styleUrls: ['./room-info.component.scss']
})
export class RoomInfoComponent implements OnInit {

  @Input()
  room: Room;

  @Input()
  filter: Filter;

  data$: Observable<TableElement[]>;
  displayedColumns: string[] = ['name', 'value'];

  constructor(private roomService: RoomService) { }

  ngOnInit() {
    this.initData();
  }

  initData() {
    this.data$ = forkJoin([
      this.roomService.getSurface(this.room),
      this.roomService.getCubature(this.room),
      this.roomService.getLighting(this.room),
      this.roomService.getHeating(this.room)
    ]).pipe(first(), map(result => {
      return [
          {name: 'Surface', value: result[0]},
          {name: 'Cubature', value: result[1]},
          {name: 'Lighting', value: result[2]},
          {name: 'Heating', value: result[3]}
        ];
      }));
  }

  shouldBeMarked(data: TableElement[]): boolean {
    if (this.filter) {
      return data[0].value > this.filter.minSurface &&
      data[0].value  < this.filter.maxSurface &&
      data[1].value  > this.filter.minCubature &&
      data[1].value  < this.filter.maxCubature &&
      data[2].value  > this.filter.minLighting &&
      data[2].value  < this.filter.maxLighting &&
      data[3].value  > this.filter.minHeating &&
      data[3].value  < this.filter.maxHeating;
    } else {
      return false;
    }
  }

}
