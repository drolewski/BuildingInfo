import { Immovable } from './Immovable';

export class Composite<T extends Immovable> extends Immovable {
  immoveables: T[] = [];
}
