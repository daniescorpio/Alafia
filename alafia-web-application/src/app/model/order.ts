import {Course} from './course';
import {Drink} from './drink';

export class Order {
  id: string;
  courses: Course[];
  coursesIdServed: string[];
  drinks: Drink[];

  constructor() {
  }
}
