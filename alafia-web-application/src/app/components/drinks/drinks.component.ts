import { Component, OnInit } from '@angular/core';
import {DataService} from '../../services/data.service';
import {Drink} from '../../model/drink';
import {Router} from '@angular/router';

@Component({
  selector: 'app-drinks',
  templateUrl: './drinks.component.html',
  styleUrls: ['./drinks.component.css']
})
export class DrinksComponent implements OnInit {

  selectedDrink: Drink;

  constructor(public dataService: DataService, private router: Router) { }

  ngOnInit(): void {
  }

  getDrinkKeys() {
    return Array.from(this.dataService.stock.getDrinks().keys());
  }

  selectDrink(selectedDrink: Drink) {
    this.selectedDrink = selectedDrink;
  }

  confirmSelectedDrink() {
    this.router.navigateByUrl('/welcome');
  }
}
