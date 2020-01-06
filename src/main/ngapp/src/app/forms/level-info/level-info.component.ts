import { Component, OnInit, Input } from '@angular/core';
import { Level } from 'src/app/models/level';
import { TableElement } from 'src/app/models/table-element';
import { LevelService } from 'src/app/services/level/level.service';

@Component({
  selector: 'app-level-info',
  templateUrl: './level-info.component.html',
  styleUrls: ['./level-info.component.scss']
})
export class LevelInfoComponent implements OnInit {

  @Input()
  level: Level;

  data: TableElement[];
  displayedColumns: string[] = ['name', 'value'];

  constructor(private levelService: LevelService) { }

  ngOnInit() {
    this.initData();
  }

  initData() {
    this.data = [
      {name: 'Surface', value: this.levelService.getSurface(this.level)},
      {name: 'Cubature', value: this.levelService.getCubature(this.level)},
      {name: 'Lighting', value: this.levelService.getLighting(this.level)},
      {name: 'Heating', value: this.levelService.getHeating(this.level)}
    ];
  }

}
