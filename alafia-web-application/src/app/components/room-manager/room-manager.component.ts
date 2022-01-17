import {Component, OnInit} from '@angular/core';
import {DinnerTable} from "../../model/dinnerTable";
import {DataService} from "../../services/data.service";
import {MatDialog} from "@angular/material/dialog";
import {SelectedTableComponent} from "../selected-table/selected-table.component";

@Component({
  selector: 'app-room-manager',
  templateUrl: './room-manager.component.html',
  styleUrls: ['./room-manager.component.css']
})
export class RoomManagerComponent implements OnInit {

  constructor(public dataService: DataService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.dataService.loadMockedData();
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

}
