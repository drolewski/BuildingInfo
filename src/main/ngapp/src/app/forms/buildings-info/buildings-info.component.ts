import { Component, OnInit, Input } from '@angular/core';
import { Building } from 'src/app/models/building';
import { BuildingsService } from 'src/app/services/buildings/buildings.service';
import { Observable } from 'rxjs';
import { Filter } from 'src/app/models/filter';
import { FormGroup, FormControl } from '@angular/forms';
import { MatSlideToggleChange } from '@angular/material';

@Component({
  selector: 'app-buildings-info',
  templateUrl: './buildings-info.component.html',
  styleUrls: ['./buildings-info.component.scss']
})
export class BuildingsInfoComponent implements OnInit {

  buildings$: Observable<Building[]>;

  filter: Filter;
  useFilter = false;

  filterForm = new FormGroup({
    minSurface: new FormControl(''),
    maxSurface: new FormControl(''),
    minCubature: new FormControl(''),
    maxCubature: new FormControl(''),
    minHeating: new FormControl(''),
    maxHeating: new FormControl(''),
    minLighting: new FormControl(''),
    maxLighting: new FormControl('')
  });

  constructor(private buildingsService: BuildingsService) {
  }

  ngOnInit() {
    this.buildings$ = this.buildingsService.getBuildings$();
    this.filterForm.valueChanges.subscribe(value => {
      this.filter = value;
    });
  }

  filterToggle(change: MatSlideToggleChange) {
    this.useFilter = change.checked;
    if (this.useFilter) {
      this.filter = new Filter();
      this.filterForm.setValue(this.filter);
    } else {
      this.filter = null;
    }
  }

}
