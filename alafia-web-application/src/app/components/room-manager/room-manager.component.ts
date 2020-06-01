import {Component, OnInit} from '@angular/core';
import {DinnerTable} from "../../model/dinnerTable";
import {DataService} from "../../services/data.service";
import {Restaurant} from "../../model/restaurant";
import {MatDialog} from "@angular/material/dialog";
import {SelectedTableComponent} from "../selected-table/selected-table.component";

@Component({
  selector: 'app-room-manager',
  templateUrl: './room-manager.component.html',
  styleUrls: ['./room-manager.component.css']
})
export class RoomManagerComponent implements OnInit {

  dinnerTables: DinnerTable[];

  constructor(public dataService: DataService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.dataService.getRestaurants().subscribe(
      (data: Restaurant[]) => {
        if (data.length > 0) {
          this.dinnerTables = data[0].dinnerTables;
        } else {
          this.dataService.loadMockedData();
          this.dinnerTables = this.dataService.restaurant.dinnerTables;
        }
      },
      error => {
        console.error('Error retrieving restaurant data');
      });
  }

  onTableClick(table: DinnerTable) {
    this.dataService.activeTable = table;
    this.dialog.open(SelectedTableComponent, {
      width: '75%',
      data: table,
    }).afterClosed().subscribe(
      result => {

      }, error => {
        console.log('Modal closed clicking background')
      });
  }

}
