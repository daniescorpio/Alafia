import {Injectable} from '@angular/core';
import {Restaurant} from '../model/restaurant';
import {Table} from '../model/table';
import {Client} from '../model/client';
import {Order} from '../model/order';
import {Course} from '../model/course';
import {Drink} from '../model/drink';
import {Booking} from '../model/booking';
import {Stock} from '../model/stock';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  course1: Course = new Course(
    'id_course1',
    'course1_name',
    9.4,
    'valores nutricionales plato 1',
    'proceso de obtencion plato 1',
    'historia del plato 1'
  );
  course2: Course = new Course(
    'id_course2',
    'course2_name',
    8.9,
    'valores nutricionales plato 2',
    'proceso de obtencion plato 2',
    'historia del plato 2'
  );

  drink1: Drink = new Drink(
    'id_drink1',
    'Agua',
    1.5);
  drink2: Drink = new Drink(
    'id_drink2',
    'CocaCola',
    2);

  order1: Order = new Order(
    [this.course2],
    [this.drink1, this.drink2]);
  order2: Order = new Order(
    [this.course1],
    [this.drink2]);

  client1: Client = new Client(
    'id_cliente1',
    'cliente1',
    'mail1@alafia.es',
    this.order1);
  client2: Client = new Client(
    'id_cliente2',
    'cliente2',
    'mail2@alafia.es',
    this.order2);

  booking1: Booking = new Booking(
    'id_booking1',
    this.client1,
    [this.client1, this.client2]
  );
  booking2: Booking = new Booking(
    'id_booking2',
    this.client2,
    [this.client1, this.client2]
  );

  table1: Table = new Table(
    'id_table1',
    this.booking1,
    [this.client2]);
  table2: Table = new Table(
    'id_table2',
    this.booking2,
    [this.client1]);

  restaurant: Restaurant = new Restaurant(
    [this.table1, this.table2]);

  stock: Stock = new Stock();

  activeTable: Table = this.table1;
  activeClient: Client = this.client1;

  constructor(private httpClient: HttpClient) {
    const drinksStock = new Map();
    drinksStock.set(this.drink1, 100);
    drinksStock.set(this.drink2, 90);
    this.stock.setDrinks(drinksStock);
  }

  getRestaurants() {
    return this.httpClient.get('http://localhost:8080/api/restaurants');
  }
}
