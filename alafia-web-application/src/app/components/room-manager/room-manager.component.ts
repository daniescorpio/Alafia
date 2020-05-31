import {Component, OnInit} from '@angular/core';
import {DinnerTable} from "../../model/dinnerTable";
import {DataService} from "../../services/data.service";
import {Restaurant} from "../../model/restaurant";

@Component({
  selector: 'app-room-manager',
  templateUrl: './room-manager.component.html',
  styleUrls: ['./room-manager.component.css']
})
export class RoomManagerComponent implements OnInit {

  dinnerTables: DinnerTable[];
  selectedDinnerTable: DinnerTable;

  constructor(public dataService: DataService) {
  }

  ngOnInit(): void {
    this.dataService.getRestaurants().subscribe(
      (data: Restaurant[]) => {
        this.dinnerTables = data[0].dinnerTables;
      },
      error => {
        console.error('Error retrieving restaurant data');
      })
  }

}
