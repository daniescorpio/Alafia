import { Routes } from '@angular/router';
import { LoginComponentComponent } from './components/login-component/login-component.component';
import { BookTableComponent } from './components/book-table/book-table.component';
import { AppComponent } from './app.component';
import {WelcomeComponent} from './components/welcome/welcome.component';
import {WarmUpComponent} from './components/warm-up/warm-up.component';
import {DrinksComponent} from './components/drinks/drinks.component';

const appRoutes: Routes = [
  {path: '', redirectTo: '/welcome', pathMatch: 'full'},
  { path: 'index',
    component: AppComponent
  },
  { path: 'login',
    component: LoginComponentComponent
  },
  { path: 'book-table',
    component: BookTableComponent
  },
  { path: 'welcome',
    component: WelcomeComponent
  },
  { path: 'warm-up',
    component: WarmUpComponent
  },
  { path: 'drinks',
    component: DrinksComponent
  }
];
export default appRoutes;
