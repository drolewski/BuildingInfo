import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Room } from 'src/app/models/room';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoomService {

  constructor(private http: HttpClient) { }

  public getSurface(room: Room): Observable<number> {
    return this.http.get<number>('/room/surface/' + room.id);
  }

  public getCubature(room: Room): Observable<number> {
    return this.http.get<number>('/room/cubature/' + room.id);
  }

  public getLighting(room: Room): Observable<number> {
    return this.http.get<number>('/room/lighting/' + room.id);
  }

  public getHeating(room: Room): Observable<number> {
    return this.http.get<number>('/room/heating/' + room.id);
  }
}
