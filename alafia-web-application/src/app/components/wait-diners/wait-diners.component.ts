import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';

@Component({
  selector: 'app-wait-diners',
  templateUrl: './wait-diners.component.html',
  styleUrls: ['./wait-diners.component.css']
})
export class WaitDinersComponent implements OnInit {

  constructor(public router: Router, public dataService: DataService) {
  }

  async ngOnInit() {
    let allConfirmed = false;
    while (!allConfirmed) {
      this.dataService.checkAllDinersConfirmed().subscribe((value: boolean) => {
        allConfirmed = value;
      });
      await this.dataService.delay(1000);
    }
    console.log('All confirmed');
    this.router.navigateByUrl('drinks');
  }
}
