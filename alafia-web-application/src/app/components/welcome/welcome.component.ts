import {Component, OnInit} from '@angular/core';
import {DinnerTable} from '../../model/dinnerTable';
import {DataService} from '../../services/data.service';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {SelectClientComponent} from '../select-client/select-client.component';
import {Restaurant} from "../../model/restaurant";

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  // table: DinnerTable = this.dataService.activeTable;
  // table: DinnerTable;

  constructor(public dataService: DataService, private router: Router, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.dataService.loadMockedData();
  }

  confirmClientInTable() {
    this.router.navigateByUrl('/warm-up');
  }

  selectOtherClient() {
    this.dialog.open(SelectClientComponent, {
      width: '250px',
      data: this.dataService.restaurant
    }).afterClosed().subscribe(result => {

    });
  }
}
