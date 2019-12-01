import { Injectable } from '@angular/core';
import { Building } from 'src/app/models/building';
import { Observable, BehaviorSubject } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import {tap} from 'rxjs/operators';
import { Level } from 'src/app/models/level';

@Injectable({
  providedIn: 'root'
})
export class BuildingsService {

  private buildings$ = new BehaviorSubject<Building[]>(null);

  constructor(private http: HttpClient) {
    this.updateBuildings$();
  }

  public getBuildings$(): Observable<Building[]> {
    return this.buildings$.asObservable();
  }

  public getSurface(building: Building): Observable<number> {
    return this.http.get<number>('/building/surface/' + building.id);
  }

  public getCubature(building: Building): Observable<number> {
    return this.http.get<number>('/building/cubature/' + building.id);
  }

  public getLighting(building: Building): Observable<number> {
    return this.http.get<number>('/building/lighting/' + building.id);
  }

  public getHeating(building: Building): Observable<number> {
    return this.http.get<number>('/building/heating/' + building.id);
  }

  public createBuilding(building: Building): Observable<void> {
    return this.http.post('/building', building).pipe(tap(() => {
      this.updateBuildings$();
    })) as Observable<void>;
  }

  public updateBuilding(building: Building): Observable<void> {
    return this.http.put('/building', building).pipe(tap(() => {
      this.updateBuildings$();
    })) as Observable<void>;
  }

  public deleteBuilding(building: Building): Observable<void> {
    return this.http.delete('/building/' + building.id).pipe(tap(() => {
      this.updateBuildings$();
    })) as Observable<void>;
  }

  public deleteLevel(level: Level): Observable<void> {
    return this.http.delete('/level/' + level.id).pipe(tap(() => {
      this.updateBuildings$();
    })) as Observable<void>;
  }

  private updateBuildings$() {
    this.http.get('/building').subscribe((result: Building[]) => {
      this.buildings$.next(result);
    });
  }
}
