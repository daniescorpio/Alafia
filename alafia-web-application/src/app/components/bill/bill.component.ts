import { Component, OnInit } from '@angular/core';
import {DataService} from "../../services/data.service";
import {Client} from "../../model/client";

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {

  client: Client;

  constructor(public dataService: DataService) { }

  ngOnInit(): void {
    this.dataService.getClientData().subscribe((data: Client) => {
      this.client = data;
    });
  }

}
