import { Component, OnInit, Input } from '@angular/core';
import { Level } from 'src/app/models/level';
import { TableElement } from 'src/app/models/table-element';
import { LevelService } from 'src/app/services/level/level.service';
import { Observable, forkJoin } from 'rxjs';
import { first, map } from 'rxjs/operators';
import { Filter } from 'src/app/models/filter';

@Component({
  selector: 'app-level-info',
  templateUrl: './level-info.component.html',
  styleUrls: ['./level-info.component.scss']
})
export class LevelInfoComponent implements OnInit {

  @Input()
  level: Level;

  data$: Observable<TableElement[]>;
  displayedColumns: string[] = ['name', 'value'];

  @Input()
  filter: Filter;

  constructor(private levelService: LevelService) { }

  ngOnInit() {
    this.initData();
  }

  initData() {
    this.data$ = forkJoin([
      this.levelService.getSurface(this.level),
      this.levelService.getCubature(this.level),
      this.levelService.getLighting(this.level),
      this.levelService.getHeating(this.level)
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
