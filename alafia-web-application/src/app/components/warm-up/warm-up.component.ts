import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {SelectClientComponent} from '../select-client/select-client.component';
import {MatDialog} from '@angular/material/dialog';
import {Client} from '../../model/client';
import {AddClientComponent} from '../add-client/add-client.component';

@Component({
  selector: 'app-warm-up',
  templateUrl: './warm-up.component.html',
  styleUrls: ['./warm-up.component.css']
})
export class WarmUpComponent implements OnInit {

  diner: Client;

  constructor(private router: Router, public dataService: DataService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  confirmDinerInTable(diner: Client) {
    this.dataService.activeClient = diner;
    this.router.navigateByUrl('/drinks');
  }

  selectOtherClient() {
    this.dialog.open(AddClientComponent, {
      width: '75%',
      data: this.dataService.restaurant
    }).afterClosed().subscribe(result => {
      console.log(this.dataService.activeClient.mail);
      if (result) {
        this.router.navigateByUrl('/drinks');
      }
    });
  }
}
