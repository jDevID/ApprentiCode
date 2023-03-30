import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {UserComponent} from './components/user/user.component';
import {ProjectComponent} from './modules/project/project.component';
import {ComplexityComponent} from './components/complexity/complexity.component';
import {EntryComponent} from './components/entry/entry.component';
import {ResourceComponent} from './components/resource/resource.component';
import {TagComponent} from './components/tag/tag.component';
//  create forms
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {UpdateUserComponent} from './components/user/update-user/update-user.component';
//  materialize deep purple - amber
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
//  pagination to Material table
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatTableModule} from "@angular/material/table";
//  filtering and sorting
import {MatSortModule} from '@angular/material/sort';
import {MatInputModule} from '@angular/material/input';
// interceptor for validation
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {ErrorInterceptor} from './interceptors/error.interceptor';
// responsive with angular material
import {MatGridListModule} from '@angular/material/grid-list';
import {ShortcutsComponent} from './shortcuts/shortcuts.component';
// Notif and alerts
import {MatSnackBarModule} from '@angular/material/snack-bar';
// load progress bar
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import {SearchBarComponent} from './components/search-bar/search-bar.component';
// personalize search-bar
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatButtonModule} from "@angular/material/button";
import { NavigationComponent } from './components/navigation/navigation.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';


@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    ProjectComponent,
    ComplexityComponent,
    EntryComponent,
    ResourceComponent,
    TagComponent,
    UpdateUserComponent,
    ShortcutsComponent,
    SearchBarComponent,
    NavigationComponent,
    LoginComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatInputModule,
    MatGridListModule,
    FormsModule,
    MatSnackBarModule,
    MatProgressSpinnerModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  providers: [
    // ...
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true},
  ],

  bootstrap: [AppComponent]
})
export class AppModule {
}
