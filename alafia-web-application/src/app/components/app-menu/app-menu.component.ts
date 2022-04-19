import {Component, Inject, OnInit} from '@angular/core';
import {DataService} from '../../services/data.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {Client} from '../../model/client';
import {Drink} from '../../model/drink';
import {DrinkDto} from '../../model/dto/drinkDto';
import {MigrationTestComponent} from '../migration-test/migration-test.component';
import {ExtrasComponent} from '../extras/extras.component';
import {Router} from '@angular/router';
import {UpdateTableDto} from '../../model/dto/updateTableDto';

@Component({
  selector: 'app-app-menu',
  templateUrl: './app-menu.component.html',
  styleUrls: ['./app-menu.component.css']
})
export class AppMenuComponent implements OnInit {

  constructor(public dataService: DataService,
              public dialog: MatDialog,
              public router: Router) {
  }

  async ngOnInit() {
    console.log('asking for launch experience');
    this.askCoursesStatus();
  }

  onDrinkClick() {
    this.dialog.open(ModalDrinkComponent, {
      width: '75%',
      height: '75%',
      data: this.dataService.activeClient,
    }).afterClosed().subscribe(
      result => {

      }, error => {
        console.log('Modal closed clicking background');
      });
  }

  onExperienceManagerClick() {
    this.dataService.experienceManagerRequest = !this.dataService.experienceManagerRequest;
    const updateTableDto = new UpdateTableDto(this.dataService.activeTable.id, this.dataService.restaurant.id);
    this.dataService.patchTableNotification(updateTableDto);
  }

  onExtrasClick() {
    this.dialog.open(ExtrasComponent, {
      width: '75%',
      height: '75%',
      data: this.dataService.activeClient,
    }).afterClosed().subscribe(
      result => {

      }, error => {
        console.log('Modal closed clicking background');
      });
  }

  onMigrationTestClick() {
    this.dialog.open(MigrationTestComponent, {
      width: '75%',
      height: '80%',
      data: this.dataService.activeClient,
    }).afterClosed().subscribe(
      result => {

      }, error => {
        console.log('Modal closed clicking background');
      });
  }

  async askCoursesStatus() {
    let actualCoursesIdServed = this.dataService.activeClient.order.coursesIdServed;
    console.log('actualCoursesIdServed: ');
    console.log(actualCoursesIdServed);
    const courses = this.dataService.activeClient.order.courses;
    let updatedCourseId;
    do {
      this.dataService.getClientData().subscribe((data: Client) => {
        console.log('Checking diffs to launch experience...');
        updatedCourseId = data.order.coursesIdServed.filter(item => actualCoursesIdServed.indexOf(item) < 0);
        console.log('differences: ');
        console.log(updatedCourseId);
        this.dataService.activeClient.order.coursesIdServed = data.order.coursesIdServed;
        actualCoursesIdServed = data.order.coursesIdServed;
        if (updatedCourseId.length === 1) {
          this.launchExperience(updatedCourseId[0]);
        }
      });
      await this.dataService.delay(5000);
    } while (courses.length !== actualCoursesIdServed.length);
  }

  private launchExperience(updatedCourseId: string) {
    this.dataService.courseIdToLaunchExperience = updatedCourseId;
    this.router.navigateByUrl('experience');
  }
}

@Component({
  // tslint:disable-next-line:component-selector
  selector: 'modal-drink',
  templateUrl: 'modal-drink.html',
  styleUrls: ['./app-menu.component.css']
})
export class ModalDrinkComponent implements OnInit {

  selectedDrink: Drink;
  drinks: Drink[];

  constructor(public dialogRef: MatDialogRef<ModalDrinkComponent>,
              @Inject(MAT_DIALOG_DATA) public data: Client,
              public dataService: DataService,
              public router: Router) {
  }

  async ngOnInit() {
    this.getDrinkKeys();
  }

  getDrinkKeys() {
    this.dataService.getDrinks().subscribe((data: Drink[]) => {
      console.log('Retrieved ' + data.length + ' drinks from DB');
      this.drinks = data;
    });
  }

  selectDrink(selectedDrink: Drink) {
    this.selectedDrink = selectedDrink;
  }

  confirmSelectedDrink() {
    const drinkDto: DrinkDto = new DrinkDto(
      this.selectedDrink.id,
      this.dataService.activeClient.order.id,
      this.dataService.activeClient.id,
      this.dataService.activeTable.booking.id,
      this.dataService.activeTable.id,
      this.dataService.restaurant.id
    );
    this.dataService.addDrinkToClient(drinkDto);
    this.dialogRef.close();
  }
}
