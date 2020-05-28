import {Course} from './course';
import {Drink} from './drink';

export class Order {
  courses: Course[];
  drinks: Drink[];

  constructor(courses: Course[], drinks: Drink[]) {
    this.courses = courses;
    this.drinks = drinks;
  }
}
