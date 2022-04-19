import {Component, OnInit} from '@angular/core';
import {DinnerTable} from '../../model/dinnerTable';
import {DataService} from '../../services/data.service';
import {MatDialog} from '@angular/material/dialog';
import {SelectedTableComponent} from '../selected-table/selected-table.component';
import {Restaurant} from "../../model/restaurant";
import {UpdateTableDto} from "../../model/dto/updateTableDto";

@Component({
  selector: 'app-room-manager',
  templateUrl: './room-manager.component.html',
  styleUrls: ['./room-manager.component.css']
})
export class RoomManagerComponent implements OnInit {

  constructor(public dataService: DataService, public dialog: MatDialog) {
  }

  async ngOnInit() {
    this.refreshData();
  }

  onTableClick(table: DinnerTable) {
    this.dataService.activeTable = table;
    this.dialog.open(SelectedTableComponent, {
      width: '75%',
      height: '80%',
      data: table,
    }).afterClosed().subscribe(
      result => {

      }, error => {
        console.log('Modal closed clicking background');
      });
  }

  async refreshData() {
    do {
      this.dataService.getRestaurants().subscribe((data: Restaurant[]) => {
        console.log('Retrieved ' + data.length + ' restaurants from server');
        console.log(data);
        if (data.length > 0) {
          this.dataService.restaurant = data[0];
        }
      });
      console.log('restaurant:');
      console.log(this.dataService.restaurant);
      await this.dataService.delay(5000);
    } while (true);
  }

  removeNotification() {
    const updateTableDto = new UpdateTableDto(this.dataService.activeTable.id, this.dataService.restaurant.id);
    this.dataService.patchTableNotification(updateTableDto);
  }

}
