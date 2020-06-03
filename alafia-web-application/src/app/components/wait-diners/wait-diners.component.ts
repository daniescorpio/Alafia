import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-wait-diners',
  templateUrl: './wait-diners.component.html',
  styleUrls: ['./wait-diners.component.css']
})
export class WaitDinersComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  skipExperience() {
    this.router.navigateByUrl('app-menu');
  }
}
