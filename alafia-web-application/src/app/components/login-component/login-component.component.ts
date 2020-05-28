import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login-component',
  templateUrl: './login-component.component.html',
  styleUrls: ['./login-component.component.css']
})
export class LoginComponentComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl('', [
      Validators.required,
      Validators.minLength(2)
    ]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(2)
    ]),
  });

  constructor(public loginService: LoginService) {
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.loginService.onClickLogin(this.loginForm.value);
  }

}
