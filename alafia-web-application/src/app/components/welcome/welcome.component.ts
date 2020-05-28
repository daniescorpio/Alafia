import {Component, OnInit} from '@angular/core';
import {Table} from '../../model/table';
import {DataService} from '../../services/data.service';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {SelectClientComponent} from '../select-client/select-client.component';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  table: Table = this.dataService.activeTable;

  constructor(public dataService: DataService, private router: Router, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.dataService.getRestaurants().subscribe((data) => {
      console.log(data);
    });
  }

  confirmClientInTable() {
    this.router.navigateByUrl('/warm-up');
  }

  selectOtherClient() {
    this.dialog.open(SelectClientComponent, {
      width: '250px',
      data: this.dataService.restaurant
    }).afterClosed().subscribe(result => {
      this.table = this.dataService.activeTable;
    });
  }
}
