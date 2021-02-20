import {Component, OnInit} from '@angular/core';
import {DataService} from '../../services/data.service';
import {Drink} from '../../model/drink';
import {Router} from '@angular/router';
import {DrinkDto} from '../../model/dto/drinkDto';

@Component({
  selector: 'app-drinks',
  templateUrl: './drinks.component.html',
  styleUrls: ['./drinks.component.css']
})
export class DrinksComponent implements OnInit {

  selectedDrink: Drink;
  drinks: Drink[];

  constructor(public dataService: DataService, private router: Router) {
  }

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
    let drinkDto: DrinkDto = new DrinkDto(
      this.selectedDrink.id,
      this.dataService.activeClient.order.id,
      this.dataService.activeClient.id,
      this.dataService.activeTable.booking.id,
      this.dataService.activeTable.id,
      this.dataService.restaurant.id
    );

    this.dataService.addDrinkToClient(drinkDto);
    this.router.navigateByUrl('/app-menu');
  }

  noAddDrink() {
    this.router.navigateByUrl('/app-menu');
  }
}
