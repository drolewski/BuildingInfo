import { Component, OnInit, Input } from '@angular/core';
import { Observable } from 'rxjs';
import { BuildingsService } from 'src/app/services/buildings/buildings.service';
import { Building } from 'src/app/models/building';

export interface TableElement {
  name: string;
  value: Observable<number>;
}

@Component({
  selector: 'app-building-info',
  templateUrl: './building-info.component.html',
  styleUrls: ['./building-info.component.scss']
})
export class BuildingInfoComponent implements OnInit {

  @Input()
  building: Building;

  data: TableElement[];
  displayedColumns: string[] = ['name', 'value'];

  constructor(private buildingsService: BuildingsService) { }

  ngOnInit() {
    this.initData();
  }

  initData() {
    this.data = [
      {name: 'Surface', value: this.buildingsService.getSurface(this.building)},
      {name: 'Cubature', value: this.buildingsService.getCubature(this.building)},
      {name: 'Lighting', value: this.buildingsService.getLighting(this.building)},
      {name: 'Heating', value: this.buildingsService.getHeating(this.building)}
    ];
  }

}
