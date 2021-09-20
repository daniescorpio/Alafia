import { Component, OnInit } from '@angular/core';
import {DataService} from '../../services/data.service';
import {Client} from '../../model/client';
import {Router} from '@angular/router';

@Component({
  selector: 'app-bill',
  templateUrl: './bill.component.html',
  styleUrls: ['./bill.component.css']
})
export class BillComponent implements OnInit {

  client: Client;
  total: number;
  totalDrinks: number;
  totalCourses: number;
  constructor(public dataService: DataService, private router: Router) { }

  ngOnInit(): void {
    this.total = 0;
    this.totalDrinks = 0;
    this.totalCourses = 0;
    this.dataService.getClientData().subscribe((data: Client) => {
      this.client = data;
      data.order.courses.forEach(c => this.total += c.price);
      data.order.courses.forEach(c => this.totalCourses += c.price);
      data.order.drinks.forEach(d => this.total += d.price);
      data.order.drinks.forEach(d => this.totalDrinks += d.price);
    });
  }

  backToMenu() {
    this.router.navigateByUrl('/app-menu');
  }
}
