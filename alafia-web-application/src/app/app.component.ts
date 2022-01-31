import {Component} from '@angular/core';
import {LoginService} from './services/login.service';
import {Router} from '@angular/router';
import {DataService} from './services/data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'alafia-web-application';

  constructor(public loginService: LoginService, private router: Router, public dataService: DataService) {
  }

  logOut() {
    this.loginService.logged = false;
    console.log('Logged out successfully user: ' + this.loginService.user);
  }

  onClick(page: string) {
    this.router.navigateByUrl(page);
  }
}
