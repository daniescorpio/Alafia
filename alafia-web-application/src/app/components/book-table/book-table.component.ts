import {Component, Inject, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {Router} from '@angular/router';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';
import {User} from '../../model/user';

@Component({
  selector: 'app-book-table',
  templateUrl: './book-table.component.html',
  styleUrls: ['./book-table.component.css']
})
export class BookTableComponent implements OnInit {

  dinersInTable: number;
  diners: User[] = [
    {id: 0, name: '', mail: '', menuType: ''},
    {id: 1, name: '', mail: '', menuType: ''},
    {id: 2, name: '', mail: '', menuType: ''},
    {id: 3, name: '', mail: '', menuType: ''},
    {id: 4, name: '', mail: '', menuType: ''},
    {id: 5, name: '', mail: '', menuType: ''}
  ];

  constructor(public loginService: LoginService, private router: Router, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    if (!this.loginService.logged) {
      this.router.navigateByUrl('/login');
    }
  }

  triggerDiners() {
    console.log('Table configured with ' + this.dinersInTable + ' diners');
    console.log(this.diners.slice(0, this.dinersInTable));
  }

  showMenuInfo(i): void {
    this.dialog.open(MenuInfoComponent, {
      width: '250px',
      data: {diner: this.diners[i].id, menuType: this.diners[i].menuType}
    });
  }

  allDinersWithMenu(): boolean {
    this.diners.forEach(user => {
      if (this.isUserFullyFilled(user)) {
        this.dinersInTable++;
      }
    });
    for (let i = 0; i < this.dinersInTable; i++) {
      if (this.diners[i].menuType === '') {
        return false;
      }
    }
    return true;
  }

  isUserFullyFilled(user: User) {
    if (user.name.length < 1 || user.mail.length < 1 || user.menuType.length < 1) {
      return false;
    }
    return true;
  }
}

@Component({
  selector: 'app-menu-info',
  templateUrl: 'menu-info.html',
  styleUrls: ['./book-table.component.css']
})
export class MenuInfoComponent {

  constructor(
    public dialogRef: MatDialogRef<MenuInfoComponent>,
    @Inject(MAT_DIALOG_DATA) public data: User) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}
