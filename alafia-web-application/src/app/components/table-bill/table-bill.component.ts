import {Component, OnInit} from '@angular/core';
import {DinnerTable} from '../../model/dinnerTable';
import {DataService} from '../../services/data.service';
import {TableBill} from '../../model/tableBill';
import {Router} from '@angular/router';

@Component({
  selector: 'app-table-bill',
  templateUrl: './table-bill.component.html',
  styleUrls: ['./table-bill.component.css']
})
export class TableBillComponent implements OnInit {

  table: DinnerTable;
  tableBill: TableBill;
  totalPrice: number;
  totalDrinks: number;
  totalCourses: number;

  constructor(public dataService: DataService, private router: Router) {
    this.table = dataService.activeTable;
    console.log('Table: ');
    console.log(this.table);
  }

  ngOnInit(): void {
    this.totalPrice = 0;
    this.totalCourses = 0;
    this.totalDrinks = 0;
    this.dataService.getTableBill().subscribe((data: TableBill) => {
      this.tableBill = data;
      this.tableBill.courses.forEach(course => {
        this.totalPrice += course.price;
        this.totalCourses += course.price;
      });
      this.tableBill.drinks.forEach(drink => {
        this.totalPrice += drink.price;
        this.totalDrinks += drink.price;
      });
      console.log('Table bill: ');
      console.log(this.tableBill);
    });
  }

  backToMenu() {
    this.router.navigateByUrl('/app-menu');
  }

}
