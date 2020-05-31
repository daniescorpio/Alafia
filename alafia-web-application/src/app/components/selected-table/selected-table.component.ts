import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA} from "@angular/material/dialog";
import {DinnerTable} from "../../model/dinnerTable";
import {Client} from "../../model/client";
import {DataService} from "../../services/data.service";
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

  setCourseStatus(clientId: string, courseId: string) {
    this.dataService.updateCourseStatus(courseId, clientId);
  }
}
