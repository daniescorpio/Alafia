import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {DataService} from '../../services/data.service';
import {Drink} from "../../model/drink";
import {CourseDto} from "../../model/dto/courseDto";
import {DomSanitizer} from "@angular/platform-browser";

@Component({
  selector: 'app-experience',
  templateUrl: './experience.component.html',
  styleUrls: ['./experience.component.css']
})
export class ExperienceComponent implements OnInit {

  urlVideo: string;

  constructor(private router: Router, public dataService: DataService, public sanitizer: DomSanitizer) {
  }

  async ngOnInit() {
    this.urlVideo = this.dataService.activeClient.order.courses
      .find(course => course.id === this.dataService.courseIdToLaunchExperience)
      .urlVideo;
  }

  skipExperience() {
    this.dataService.courseIdToLaunchExperience = null;
    this.router.navigateByUrl('app-menu');
  }
}
