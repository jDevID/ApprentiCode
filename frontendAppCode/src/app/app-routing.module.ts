import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProjectComponent} from './components/project/project.component';
import {ComplexityComponent} from './components/complexity/complexity.component';
import {EntryComponent} from './components/entry/entry.component';
import {ResourceComponent} from './components/resource/resource.component';
import {MatTableModule} from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';

const routes: Routes = [
  {path: 'projects', component: ProjectComponent},
  {path: 'complexities', component: ComplexityComponent},
  {path: 'entries', component: EntryComponent},
  {path: 'resources', component: ResourceComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes),

  ],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
