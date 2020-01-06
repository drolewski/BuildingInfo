import { Observable } from 'rxjs';

export interface TableElement {
  name: string;
  value: Observable<number>;
}
