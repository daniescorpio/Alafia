import {Component, Inject, OnInit} from '@angular/core';
import {DataService} from "../../services/data.service";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {Client} from "../../model/client";
import {Drink} from "../../model/drink";
import {DrinkDto} from "../../model/dto/drinkDto";

@Component({
  selector: 'app-app-menu',
  templateUrl: './app-menu.component.html',
  styleUrls: ['./app-menu.component.css']
})
export class AppMenuComponent implements OnInit {

  constructor(public dataService: DataService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  onDrinkClick() {
    this.dialog.open(ModalDrinkComponent, {
      width: '75%',
      height: '75%',
      data: this.dataService.activeClient,
    }).afterClosed().subscribe(
      result => {

      }, error => {
        console.log('Modal closed clicking background')
      });
  }

  onExperienceManagerClick() {

  }

  onExtrasClick() {

  }

  onMigrationTestClick() {

  }
}

@Component({
  selector: 'modal-drink',
  templateUrl: 'modal-drink.html',
  styleUrls: ['./app-menu.component.css']
})
export class ModalDrinkComponent implements OnInit {

  selectedDrink: Drink;
  drinks: Drink[];

  constructor(public dialogRef: MatDialogRef<ModalDrinkComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Client,
              public dataService: DataService) {
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
  }
}
