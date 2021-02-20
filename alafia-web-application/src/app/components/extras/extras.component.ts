import {Component, Inject, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Client} from "../../model/client";
import {Booking} from "../../model/booking";

@Component({
  selector: 'app-extras',
  templateUrl: './extras.component.html',
  styleUrls: ['./extras.component.css']
})
export class ExtrasComponent implements OnInit {

  constructor(private router: Router,
              @Inject(MAT_DIALOG_DATA) public data: Client,
              public dialogRef: MatDialogRef<ExtrasComponent>) { }

  ngOnInit(): void {
  }

  billRequest() {
    this.router.navigateByUrl('/bill');
    this.dialogRef.close();
  }

  fullBillRequest() {
    this.router.navigateByUrl('/bill');
    this.dialogRef.close();
  }
}
