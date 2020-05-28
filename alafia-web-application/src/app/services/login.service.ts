import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  logged;
  user;
  password;

  constructor(private router: Router) {
    this.user = '';
    this.password = '';
    this.logged = false;
  }



  public onClickLogin(value: any) {
    if (value.email === 'alafia@alafia.es' && value.password === 'alafia') {
      this.router.navigateByUrl('/welcome');
      this.user = value.email;
      this.password = value.password;
      this.logged = true;
      console.log('Login successfully with user ' + this.user);
    } else {
      console.log('Login Failed');
    }
  }
}
