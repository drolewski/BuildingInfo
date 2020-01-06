import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Level } from 'src/app/models/level';

@Injectable({
  providedIn: 'root'
})
export class LevelService {

  constructor(private http: HttpClient) { }

  public getSurface(level: Level): Observable<number> {
    return this.http.get<number>('/level/surface/' + level.id);
  }

  public getCubature(level: Level): Observable<number> {
    return this.http.get<number>('/room/cubature/' + level.id);
  }

  public getLighting(level: Level): Observable<number> {
    return this.http.get<number>('/room/lighting/' + level.id);
  }

  public getHeating(level: Level): Observable<number> {
    return this.http.get<number>('/room/heating/' + level.id);
  }
}
