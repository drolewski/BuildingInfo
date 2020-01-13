import { Component, OnInit, Input } from '@angular/core';
import { Observable, of, forkJoin } from 'rxjs';
import { BuildingsService } from 'src/app/services/buildings/buildings.service';
import { Building } from 'src/app/models/building';
import { TableElement} from 'src/app/models/table-element';
import { Filter } from 'src/app/models/filter';
import { concatMap, map, first } from 'rxjs/operators';

@Component({
  selector: 'app-building-info',
  templateUrl: './building-info.component.html',
  styleUrls: ['./building-info.component.scss']
})
export class BuildingInfoComponent implements OnInit {

  @Input()
  building: Building;

  @Input()
  filter: Filter;

  data$: Observable<TableElement[]>;
  displayedColumns: string[] = ['name', 'value'];

  constructor(private buildingsService: BuildingsService) { }

  ngOnInit() {
    this.initData();
  }

  initData() {
    this.data$ = forkJoin([
      this.buildingsService.getSurface(this.building),
      this.buildingsService.getCubature(this.building),
      this.buildingsService.getLighting(this.building),
      this.buildingsService.getHeating(this.building)
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
