import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {DataService} from '../../services/data.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {ClientDto} from "../../model/dto/clientDto";
import {Client} from "../../model/client";

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

  email: string;
  name: string;

  constructor(public dialogRef: MatDialogRef<AddClientComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Client,
              public dataService: DataService) {
  }

  ngOnInit(): void {
  }

  onGoBack() {
    this.loginForm.value.email = null;
    this.loginForm.value.name = null;
    this.dialogRef.close();
  }

  onSubmit() {
    if (this.loginForm.value.email === null || this.loginForm.value.name === null) {
      this.dialogRef.close(false);
    }
    else {
      let newClient = new ClientDto(
        this.loginForm.value.name,
        this.loginForm.value.email,
        this.data.id,
        this.dataService.activeTable.booking.id,
        this.dataService.activeTable.id,
        this.dataService.restaurant.id,
        this.data.confirmed
      );

      this.dataService.postClient(newClient).subscribe((data: Client) => {
        console.log(data);
        let findIndex = this.dataService.activeTable.booking.diners.findIndex(c => c.id === data.id);
        this.dataService.activeTable.booking.diners.splice(findIndex, 1);
        this.dataService.activeTable.booking.diners.push(data);
        this.dataService.activeClient = data;
        console.log(this.dataService.activeClient);
        this.dialogRef.close(true);
      });
    }
  }
}
