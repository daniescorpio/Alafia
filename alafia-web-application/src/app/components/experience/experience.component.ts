import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {DataService} from "../../services/data.service";

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  constructor(private router: Router, public dataService: DataService) { }

  ngOnInit(): void {
  }

  skipExperience() {
    this.dataService.courseIdToLaunchExperience = null;
    this.router.navigateByUrl('app-menu');
  }
}
