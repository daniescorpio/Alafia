import { Routes } from '@angular/router';
import { LoginComponentComponent } from './components/login-component/login-component.component';
import { BookTableComponent } from './components/book-table/book-table.component';
import { AppComponent } from './app.component';
import {WelcomeComponent} from './components/welcome/welcome.component';
import {WarmUpComponent} from './components/warm-up/warm-up.component';
import {DrinksComponent} from './components/drinks/drinks.component';
import {RoomManagerComponent} from './components/room-manager/room-manager.component';
import {ExperienceComponent} from './components/experience/experience.component';
import {AppMenuComponent} from './components/app-menu/app-menu.component';
import {ExperienceManagerComponent} from './components/experience-manager/experience-manager.component';
import {ExtrasComponent} from './components/extras/extras.component';
import {MigrationTestComponent} from './components/migration-test/migration-test.component';
import {WaitDinersComponent} from './components/wait-diners/wait-diners.component';
import {BillComponent} from './components/bill/bill.component';
import {ReplaceClientComponent} from './components/replace-client/replace-client.component';
import {TableBillComponent} from "./components/table-bill/table-bill.component";

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
  { path: 'replace-client',
    component: ReplaceClientComponent
  },
  { path: 'drinks',
    component: DrinksComponent
  },
  { path: 'wait-diners',
    component: WaitDinersComponent
  },
  { path: 'app-menu',
    component: AppMenuComponent
  },
  { path: 'experience-manager',
    component: ExperienceManagerComponent
  },
  { path: 'extras',
    component: ExtrasComponent
  },
  { path: 'migration-test',
    component: MigrationTestComponent
  },
  { path: 'experience',
    component: ExperienceComponent
  },
  { path: 'bill',
    component: BillComponent
  },
  { path: 'table-bill',
    component: TableBillComponent
  },
  { path: 'room-manager',
    component: RoomManagerComponent
  }
];
export default appRoutes;
