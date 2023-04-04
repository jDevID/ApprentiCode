import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutComponent } from './layout/layout.component';
import {MatButtonModule} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";
import {MatToolbarModule} from "@angular/material/toolbar";
import { MatIconModule } from '@angular/material/icon';
import { ComplexityComponent } from './components/complexity/complexity.component';
import { EntryComponent } from './components/entry/entry.component';
import { ResourceComponent } from './components/resource/resource.component';
import { ProjectComponent } from './components/project/project.component';
import { TagComponent } from './components/tag/tag.component';
import {MatTableModule} from "@angular/material/table";
import {MatPaginatorModule} from "@angular/material/paginator";
import {MatSortModule} from "@angular/material/sort";
import {EntityService} from "./services/entity.service";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";

@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    ComplexityComponent,
    EntryComponent,
    ResourceComponent,
    ProjectComponent,
    TagComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatMenuModule,
    MatToolbarModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatSidenavModule,
    MatListModule,
  ],
  providers: [
    EntityService
  ],

  bootstrap: [AppComponent]
})
export class AppModule {
}
