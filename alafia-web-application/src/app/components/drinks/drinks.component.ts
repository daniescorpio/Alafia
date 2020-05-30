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
  drinks: Drink[];

  constructor(public dataService: DataService, private router: Router) { }

  ngOnInit(): void {
    this.getDrinkKeys();
  }

  getDrinkKeys() {
    this.dataService.getDrinks().subscribe((data: Drink[]) => {
      console.log('Retrieved ' + data.length + ' drinks from DB')
      this.drinks = data;
    });
  }

  selectDrink(selectedDrink: Drink) {
    this.selectedDrink = selectedDrink;
  }

  confirmSelectedDrink() {
    //TODO: set selected drink into client
    this.router.navigateByUrl('/welcome');
  }
}
