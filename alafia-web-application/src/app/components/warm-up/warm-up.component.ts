import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {MatDialog} from '@angular/material/dialog';
import {Client} from '../../model/client';
import {ClientDto} from '../../model/dto/clientDto';

@Component({
  selector: 'app-warm-up',
  templateUrl: './warm-up.component.html',
  styleUrls: ['./warm-up.component.css']
})
export class WarmUpComponent implements OnInit {

  constructor(private router: Router, public dataService: DataService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
  }

  confirmDinerInTable(diner: Client) {
    this.dataService.updateClientStatus(new ClientDto(
      diner.name,
      diner.mail,
      diner.id,
      this.dataService.activeTable.booking.id,
      this.dataService.activeTable.id,
      this.dataService.restaurant.id,
      diner.confirmed
    )).subscribe((data: Client) => {
      const index = this.dataService.activeTable.booking.diners.findIndex(c => c.id === data.id);
      this.dataService.activeTable.booking.diners.splice(index, 1);
      this.dataService.activeTable.booking.diners.push(data);
      this.dataService.activeClient = data;
    });
    if (!diner.confirmed) {
      console.log('Dinner ' + diner.name + ' with id ' + diner.id + ' has confirmed his seat on the table');
    } else {
      console.log('Dinner ' + diner.name + ' with id ' + diner.id + ' has refused his seat on the table');
    }
    this.router.navigateByUrl('/wait-diners');
  }

  selectOtherClient() {
    this.router.navigateByUrl('replace-client');
  }
}
