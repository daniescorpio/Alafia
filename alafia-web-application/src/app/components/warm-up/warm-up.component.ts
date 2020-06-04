import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {SelectClientComponent} from '../select-client/select-client.component';
import {MatDialog} from '@angular/material/dialog';
import {Client} from '../../model/client';
import {AddClientComponent} from '../add-client/add-client.component';
import {ClientDto} from "../../model/dto/clientDto";

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
      let index = this.dataService.activeTable.booking.diners.findIndex(c => c.id === data.id);
      this.dataService.activeTable.booking.diners.splice(index, 1);
      this.dataService.activeTable.booking.diners.push(data);
      this.dataService.activeClient = data;
    });
    this.router.navigateByUrl('/drinks');
  }

  selectOtherClient() {
    this.router.navigateByUrl('replace-client')
  }
}
