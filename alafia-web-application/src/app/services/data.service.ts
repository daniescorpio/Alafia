import {Injectable} from '@angular/core';
import {Restaurant} from '../model/restaurant';
import {DinnerTable} from '../model/dinnerTable';
import {Client} from '../model/client';
import {Order} from '../model/order';
import {Course} from '../model/course';
import {Drink} from '../model/drink';
import {Booking} from '../model/booking';
import {HttpClient} from '@angular/common/http';
import {ClientDto} from "../model/dto/clientDto";
import {DrinkDto} from "../model/dto/drinkDto";
import {CourseDto} from "../model/dto/courseDto";
import {BehaviorSubject, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  /*course1: Course = new Course(
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

  table1: DinnerTable = new DinnerTable(
    'id_table1',
    this.booking1,
    [this.client2]);
  table2: DinnerTable = new DinnerTable(
    'id_table2',
    this.booking2,
    [this.client1]);

  restaurant: Restaurant = new Restaurant(
    [this.table1, this.table2]);*/

  // stock: Stock = new Stock();

  apiPath = 'http://localhost:8080/api';

  restaurant: Restaurant;

  activeTable: DinnerTable;
  activeClient: Client;

  constructor(private httpClient: HttpClient) {
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  loadMockedData() {
    return this.httpClient.get(this.apiPath + '/load-data').subscribe(data => {
      console.log('Data loaded complete')
      console.log('Setting default restaurant...')
      this.setDefaultRestaurant();
    });
  }

  setDefaultRestaurant() {
    this.getRestaurants().subscribe((data: Restaurant[]) => {
      console.log('Retrieved ' + data.length + ' restaurants from server');
      console.log(data)
      if (data.length > 0) {
        this.setAllDinerTablesWithStatus(data[0]);
        this.restaurant = data[0];
        console.log('Restaurant setted: ' + this.restaurant.id)
      }
      this.setDefaultActiveDinnerTable();
    })
  }

  setAllDinerTablesWithStatus(restaurant: Restaurant) {
    restaurant.dinnerTables.forEach(table => {
      if (table.allDinersConfirmed === undefined) {
        table.allDinersConfirmed = true;
      } else {
        table.allDinersConfirmed= true;
      }
    })
    return restaurant;
  }

  setDefaultActiveDinnerTable() {
    this.activeTable = this.restaurant.dinnerTables[0];
    console.log('Active table setted: ', this.activeTable.id);
    console.log(this.activeTable);
  }

  addDrinkToClient(drinkDto: DrinkDto) {
    return this.httpClient.post(this.apiPath + '/add-drink', drinkDto)
      .subscribe(data => {
        console.log('Drink with id ' + drinkDto.drinkId + ' added in client with id ' + drinkDto.clientId)
        console.log(data)
      });
  }

  updateCourseStatus(courseDto: CourseDto) {
    return this.httpClient.post(this.apiPath + '/update-course', courseDto);
  }

  getDinnersForTable(tableId: string) {
    return this.httpClient.get(this.apiPath + '/diners/' + tableId);
  }

  updateClientStatus(client: ClientDto) {
    return this.httpClient.post(this.apiPath + '/update-client', client);
  }

  checkAllDinersConfirmed() {
    return this.httpClient.get(this.apiPath + '/clients-confirmed/' + this.activeTable.id)
    // this.activeTable.booking.diners.forEach(diner => {
    //   console.log('diner ' + diner.name + ' confirmed: ' + diner.confirmed)
    //   if (!diner.confirmed) this.activeTable.allDinersConfirmed = false;
    // });
    // return this.activeTable.allDinersConfirmed;
  }

  // ******** BASIC API OPERATIONS ********

  getRestaurants() {
    return this.httpClient.get(this.apiPath + '/restaurants');
  }

  postRestaurant(restaurant: Restaurant) {
    return this.httpClient.post(this.apiPath + '/restaurants', restaurant).subscribe(data => {
      console.log('Restaurant saved: ')
      console.log(data)
    });
  }

  getDinnerTables() {
    return this.httpClient.get(this.apiPath + '/dinner-tables');
  }

  postDinnerTable(dinnerTable: DinnerTable) {
    return this.httpClient.post(this.apiPath + '/dinner-tables', dinnerTable).subscribe(data => {
      console.log('Dinner table saved: ')
      console.log(data)
    });
  }

  getBookings() {
    return this.httpClient.get(this.apiPath + '/bookings');
  }

  postBooking(booking: Booking) {
    return this.httpClient.post(this.apiPath + '/booking', booking).subscribe(data => {
      console.log('Booking saved: ')
      console.log(data)
    });
  }

  getClients() {
    return this.httpClient.get(this.apiPath + '/clients');
  }

  postClient(client: ClientDto) {
    return this.httpClient.post(this.apiPath + '/clients', client);
  }

  getOrders() {
    return this.httpClient.get(this.apiPath + '/orders');
  }

  postOrder(order: Order) {
    return this.httpClient.post(this.apiPath + '/orders', order).subscribe(data => {
      console.log('Order saved: ')
      console.log(data)
    });
  }

  getCourses() {
    return this.httpClient.get(this.apiPath + '/courses');
  }

  postCourse(course: Course) {
    return this.httpClient.post(this.apiPath + '/courses', course).subscribe(data => {
      console.log('Course saved: ')
      console.log(data)
    });
  }

  getDrinks() {
    return this.httpClient.get(this.apiPath + '/drinks');
  }

  postDrink(drink: Drink) {
    return this.httpClient.post(this.apiPath + '/drinks', drink).subscribe(data => {
      console.log('Drink saved: ')
      console.log(data)
    });
  }
}
