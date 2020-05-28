import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Restaurant} from '../../model/restaurant';
import {DataService} from '../../services/data.service';

@Component({
  selector: 'app-select-client',
  templateUrl: './select-client.component.html',
  styleUrls: ['./select-client.component.css']
})
export class SelectClientComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<SelectClientComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Restaurant,
              public dataService: DataService) {}

  ngOnInit(): void {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  saveAsNewClient(idNewClient) {
    console.log(this.dataService.activeTable);
    this.dataService.activeTable = this.data.tables.find(table => table.booking.client.id === idNewClient);
    console.log('finding booking id: ', idNewClient);
    console.log(this.data.tables);
    console.log(this.dataService.activeTable);
    this.dialogRef.close();
  }
}
