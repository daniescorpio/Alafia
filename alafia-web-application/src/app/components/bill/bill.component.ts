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
  total: number;

  constructor(public dataService: DataService) { }

  ngOnInit(): void {
    this.total = 0;
    this.dataService.getClientData().subscribe((data: Client) => {
      this.client = data;
      data.order.courses.forEach(c => this.total += c.price);
      data.order.drinks.forEach(d => this.total += d.price);
    });
  }

}
