import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';
import {DinnerTable} from '../../model/dinnerTable';
import {Client} from '../../model/client';
import {DataService} from '../../services/data.service';
import {CourseDto} from '../../model/dto/courseDto';
import {Order} from '../../model/order';
import {Restaurant} from '../../model/restaurant';

@Component({
  selector: 'app-selected-table',
  templateUrl: './selected-table.component.html',
  styleUrls: ['./selected-table.component.css']
})
export class SelectedTableComponent implements OnInit {

  dinners: Client[];

  constructor(@Inject(MAT_DIALOG_DATA) public data: DinnerTable, public dataService: DataService) {
  }

  ngOnInit(): void {
    this.dinners = this.data.booking.diners;
  }

  setCourseStatus(courseId: string, dinerId: string) {
    this.dataService.activeClient = this.data.booking.diners.find(client => client.id === dinerId);
    console.log('New activeClient ' + this.dataService.activeClient.id);

    const courseDto: CourseDto = new CourseDto(
      courseId,
      this.dataService.activeClient.order.id,
      this.dataService.activeClient.id,
      this.dataService.activeTable.booking.id,
      this.dataService.activeTable.id,
      this.dataService.restaurant.id,
      ''
    );

    this.dataService.updateCourseStatus(courseDto)
      .subscribe((data: Order) => {
        console.log(data);
        console.log('Course with id ' + courseDto.courseId + 'updated with status ' + data.coursesIdServed.includes(courseDto.courseId));
        this.dataService.getDinnersForTable(this.dataService.activeTable.id)
          .subscribe((table: DinnerTable) => {
            this.dataService.getRestaurants().subscribe((data: Restaurant[]) => {
              if (data.length > 0) {
                this.dataService.restaurant = data[0];
              }
            });
            this.dinners = table.booking.diners;
          });
      });
  }
}
