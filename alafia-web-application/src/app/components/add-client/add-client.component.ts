import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {DataService} from '../../services/data.service';
import {Client} from '../../model/client';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Restaurant} from '../../model/restaurant';

@Component({
  selector: 'app-add-client',
  templateUrl: './add-client.component.html',
  styleUrls: ['./add-client.component.css']
})
export class AddClientComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl('', [
      Validators.required,
      Validators.minLength(2)
    ]),
    name: new FormControl('', [
      Validators.required,
      Validators.minLength(2)
    ]),
  });

  constructor(public dialogRef: MatDialogRef<AddClientComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Restaurant,
              public dataService: DataService) { }

  ngOnInit(): void {
  }

  onSubmit() {
    if (this.loginForm.value.email === null || this.loginForm.value.name === null) {
      this.dialogRef.close(false);
    }
    this.dataService.activeTable.booking.diners.push(new Client(null, this.loginForm.value.name, this.loginForm.value.email, null));
    this.dataService.activeClient = this.dataService.activeTable.booking.diners.find(client => client.mail = this.loginForm.value.email);
    this.dialogRef.close(true);
  }

}
