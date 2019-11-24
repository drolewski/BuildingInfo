import { Component, OnInit } from '@angular/core';
import { BuildingsService } from 'src/app/services/buildings/buildings.service';
import { Observable } from 'rxjs';
import { Building } from 'src/app/models/building';

@Component({
  selector: 'app-buildings-editor',
  templateUrl: './buildings-editor.component.html',
  styleUrls: ['./buildings-editor.component.scss']
})
export class BuildingsEditorComponent implements OnInit {

  buildings$: Observable<Building[]>;

  constructor(private buildingsService: BuildingsService) {
  }

  ngOnInit() {
    this.buildings$ = this.buildingsService.getBuildings$();
  }

}
