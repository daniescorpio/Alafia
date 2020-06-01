import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {DinnerTable} from "../../model/dinnerTable";
import {Client} from "../../model/client";
import {DataService} from "../../services/data.service";
import {CourseDto} from "../../model/dto/courseDto";
import {Course} from "../../model/course";

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
    if (this.dataService.activeClient === undefined) {
      this.dataService.activeClient = this.data.booking.diners.find(client => client.id === dinerId);
    }
    let courseDto: CourseDto = new CourseDto(
      courseId,
      this.dataService.activeClient.order.id,
      this.dataService.activeClient.id,
      this.dataService.activeTable.booking.id,
      this.dataService.activeTable.id,
      this.dataService.restaurant.id
    );
    this.dataService.updateCourseStatus(courseDto)
      .subscribe((data: Course) => {
        console.log('Course with id ' + courseDto.courseId + 'updated with status ' + data.served)
        this.dataService.getDinnersForTable(this.dataService.activeTable.id)
          .subscribe((table: DinnerTable) => {
            this.dinners = table.booking.diners;
          });
      });
  }
}
