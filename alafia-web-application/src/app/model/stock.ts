import {Drink} from './drink';
import {Course} from './course';

export class Stock {
  private drinks: Map<Drink, number>;
  private courses: Map<Course, number>;

  public setDrinks(drinksMap: Map<Drink, number>) {
    this.drinks = drinksMap;
  }

  public getDrinks(): Map<Drink, number> {
    return this.drinks;
  }
}
