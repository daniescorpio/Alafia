import {Component, OnInit} from '@angular/core';
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

  constructor(public dataService: DataService, private router: Router, public dialog: MatDialog) {
  }

  async ngOnInit() {
    console.log(this.dataService.restaurant);
    while (this.dataService.restaurant === undefined) {
      this.dataService.loadMockedData();
      await this.dataService.delay(10000);
    }
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
