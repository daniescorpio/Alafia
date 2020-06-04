import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {DataService} from "../../services/data.service";
import validate = WebAssembly.validate;

@Component({
  selector: 'app-wait-diners',
  templateUrl: './wait-diners.component.html',
  styleUrls: ['./wait-diners.component.css']
})
export class WaitDinersComponent implements OnInit {


  //TODO: When all dinners are confirmed in table, go to next component (app-menu)
  constructor(public router: Router, public dataService: DataService) {
    this.dataService.checkAllDinersConfirmed().subscribe(value => {
      console.log('All in table confirmed: ' + value);
      if (value) this.router.navigateByUrl('app-menu');
    });
  }

  ngOnInit(): void {
  }

  skipExperience() {
    this.router.navigateByUrl('app-menu');
  }
}
