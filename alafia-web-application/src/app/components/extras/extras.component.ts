import {Component, Inject, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Client} from '../../model/client';

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
    this.dialogRef.close();
    this.router.navigateByUrl('/bill');
  }

  fullBillRequest() {
    this.dialogRef.close();
    this.router.navigateByUrl('/table-bill');
  }

  backToMenu() {
    this.dialogRef.close();
  }
}
