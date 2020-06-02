import {BrowserModule} from '@angular/platform-browser';
import {RouterModule} from '@angular/router';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import appRoutes from './routerConfig';

import {AppComponent} from './app.component';
import {LoginComponentComponent} from './components/login-component/login-component.component';
import {BookTableComponent, MenuInfoComponent} from './components/book-table/book-table.component';
import {WelcomeComponent} from './components/welcome/welcome.component';
import {SelectClientComponent} from './components/select-client/select-client.component';
import {WarmUpComponent} from './components/warm-up/warm-up.component';
import {DrinksComponent} from './components/drinks/drinks.component';
import {AddClientComponent} from './components/add-client/add-client.component';
import {RoomManagerComponent} from './components/room-manager/room-manager.component';
import {SelectedTableComponent} from './components/selected-table/selected-table.component';
import {ExperienceComponent} from './components/experience/experience.component';
import {AppMenuComponent, ModalDrinkComponent} from './components/app-menu/app-menu.component';

import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import {MatListModule} from '@angular/material/list';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatGridListModule} from '@angular/material/grid-list';
import { ExperienceManagerComponent } from './components/experience-manager/experience-manager.component';
import { ExtrasComponent } from './components/extras/extras.component';
import { MigrationTestComponent } from './components/migration-test/migration-test.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponentComponent,
    BookTableComponent,
    MenuInfoComponent,
    WelcomeComponent,
    SelectClientComponent,
    WarmUpComponent,
    DrinksComponent,
    AddClientComponent,
    RoomManagerComponent,
    SelectedTableComponent,
    ExperienceComponent,
    AppMenuComponent,
    ModalDrinkComponent,
    ExperienceManagerComponent,
    ExtrasComponent,
    MigrationTestComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    MatButtonModule,
    MatToolbarModule,
    MatInputModule,
    MatSelectModule,
    FormsModule,
    MatIconModule,
    MatDialogModule,
    MatListModule,
    MatSidenavModule,
    HttpClientModule,
    MatGridListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
