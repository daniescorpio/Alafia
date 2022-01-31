import {Component, OnInit} from '@angular/core';
import {DataService} from '../../services/data.service';
import {Router} from '@angular/router';
import {Client} from '../../model/client';
import {AddClientComponent} from '../add-client/add-client.component';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-replace-client',
  templateUrl: './replace-client.component.html',
  styleUrls: ['./replace-client.component.css']
})
export class ReplaceClientComponent implements OnInit {

  oldClient: Client;

  constructor(public dataService: DataService, private router: Router, public dialog: MatDialog) { }

  ngOnInit(): void {
  }

  selectOtherClient(diner: Client) {
    this.dialog.open(AddClientComponent, {
      width: '75%',
      data: diner
    }).afterClosed().subscribe(result => {
      if (result) {
        console.log(this.dataService.activeClient.mail);
        this.router.navigateByUrl('/drinks');
      }
    });
  }

}
