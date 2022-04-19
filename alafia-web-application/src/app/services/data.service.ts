import {Injectable} from '@angular/core';
import {Restaurant} from '../model/restaurant';
import {DinnerTable} from '../model/dinnerTable';
import {Client} from '../model/client';
import {Order} from '../model/order';
import {Course} from '../model/course';
import {Drink} from '../model/drink';
import {HttpClient} from '@angular/common/http';
import {ClientDto} from '../model/dto/clientDto';
import {DrinkDto} from '../model/dto/drinkDto';
import {CourseDto} from '../model/dto/courseDto';
import {ClientAnswersDto} from '../model/dto/clientAnswersDto';
import {UpdateTableDto} from "../model/dto/updateTableDto";

@Injectable({
  providedIn: 'root'
})
export class DataService {

  apiPath = 'http://localhost:8080/api';

  restaurant: Restaurant;

  activeTable: DinnerTable;
  activeClient: Client;
  courseIdToLaunchExperience: string;
  experienceManagerRequest = false;


  constructor(private httpClient: HttpClient) {
  }

  delay(ms: number) {
    return new Promise( resolve => setTimeout(resolve, ms) );
  }

  loadMockedData() {
    return this.httpClient.get(this.apiPath + '/load-data').subscribe(data => {
      console.log('Data loaded complete');
      console.log('Setting default restaurant...');
      this.setDefaultRestaurant();
    });
  }

  loadDataFromServer() {
    return this.getRestaurants().subscribe(data => {
      console.log('Data loaded complete');
      console.log('Setting default restaurant...');
      this.setDefaultRestaurant();
    });
  }

  setDefaultRestaurant() {
    this.getRestaurants().subscribe((data: Restaurant[]) => {
      console.log('Retrieved ' + data.length + ' restaurants from server');
      console.log(data);
      if (data.length > 0) {
        this.setAllDinerTablesWithStatus(data[0]);
        this.restaurant = data[0];
        console.log('Restaurant setted: ' + this.restaurant.id);
      }
      this.setDefaultActiveDinnerTable();
    });
  }

  setAllDinerTablesWithStatus(restaurant: Restaurant) {
    restaurant.dinnerTables.forEach(table => {
      if (table.allDinersConfirmed === undefined) {
        table.allDinersConfirmed = true;
      } else {
        table.allDinersConfirmed = true;
      }
      if (table.activeNotification === undefined) {
        table.activeNotification = false;
      } else {
        table.activeNotification = false;
      }
    });
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
        console.log('Drink with id ' + drinkDto.drinkId + ' added in client with id ' + drinkDto.clientId);
        console.log(data);
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
    return this.httpClient.get(this.apiPath + '/clients-confirmed/' + this.activeTable.id);
  }

  // ******** BASIC API OPERATIONS ********

  getRestaurants() {
    return this.httpClient.get(this.apiPath + '/restaurants');
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
      console.log('Order saved: ');
      console.log(data);
    });
  }

  getCourses() {
    return this.httpClient.get(this.apiPath + '/courses');
  }

  getCoursesById(courseId: string) {
    return this.httpClient.get(this.apiPath + '/courses/' + courseId);
  }

  postCourse(course: Course) {
    return this.httpClient.post(this.apiPath + '/courses', course).subscribe(data => {
      console.log('Course saved: ');
      console.log(data);
    });
  }

  getDrinks() {
    return this.httpClient.get(this.apiPath + '/drinks');
  }

  postDrink(drink: Drink) {
    return this.httpClient.post(this.apiPath + '/drinks', drink).subscribe(data => {
      console.log('Drink saved: ');
      console.log(data);
    });
  }

  getClientData() {
    return this.httpClient.get(this.apiPath + '/clients/' + this.activeClient.id);
  }

  getTableBill() {
    return this.httpClient.get(this.apiPath + '/table-bill/' + this.activeTable.id);
  }

  postClientAnswers(client: ClientAnswersDto) {
    return this.httpClient.post(this.apiPath + '/migrationTest', client).subscribe(d => console.log(d));
  }

  patchTableNotification(updateTableDto: UpdateTableDto) {
    let dinnerTable = this.restaurant.dinnerTables.find(table => table.id === updateTableDto.dinnerTableId);
    console.log('Table until patch:');
    console.log(dinnerTable);
    this.httpClient.patch(this.apiPath + '/experience-manager-notification', updateTableDto)
      .subscribe(res => console.log(res));
  }
}
