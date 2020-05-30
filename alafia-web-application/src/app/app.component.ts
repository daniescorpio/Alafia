import {Component} from '@angular/core';
import {LoginService} from './services/login.service';
import {Router} from '@angular/router';
import {DataService} from "./services/data.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'alafia-web-application';

  constructor(public loginService: LoginService, private router: Router, public dataService: DataService) {
    // this.dataService.loadMockedData();
  }

  logOut() {
    this.loginService.logged = false;
    console.log('Logged out successfully user: ' + this.loginService.user);
  }

  onClick(page: string) {
    this.router.navigateByUrl(page);
  }

  // loadMockedData() {
  //   console.log('Loading data from server...')
  //   this.dataService.loadMockedData().subscribe(data => {
  //     console.log('Data loaded complete')
  //     console.log('Setting default restaurant...')
  //     this.setDefaultRestaurant();
  //   });
  // }
  //
  // setDefaultRestaurant() {
  //   this.dataService.getRestaurants().subscribe((data: Restaurant[]) => {
  //     console.log('Retrieved ' + data.length + ' restaurants from server');
  //     console.log(data)
  //     if (data.length > 0) {
  //       this.dataService.restaurant = data[0];
  //       console.log('Restaurant setted: ' + this.dataService.restaurant.id)
  //     }
  //   })
  // }
}
