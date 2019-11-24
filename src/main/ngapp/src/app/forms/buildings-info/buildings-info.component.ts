import { Component, OnInit, Input } from '@angular/core';
import { Building } from 'src/app/models/building';
import { BuildingsService } from 'src/app/services/buildings/buildings.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-buildings-info',
  templateUrl: './buildings-info.component.html',
  styleUrls: ['./buildings-info.component.scss']
})
export class BuildingsInfoComponent implements OnInit {

  buildings$: Observable<Building[]>;

  constructor(private buildingsService: BuildingsService) {
    this.buildings$ = buildingsService.getBuildings$();
  }

  ngOnInit() {
  }

}
