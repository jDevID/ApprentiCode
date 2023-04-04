import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ProjectComponent} from './project/project.component';
import { ComplexityComponent } from './complexity/complexity.component';

const routes: Routes = [
  {path: 'projects', component: ProjectComponent},
  { path: 'complexities', component: ComplexityComponent },
  { path: '', redirectTo: 'projects', pathMatch: 'full' },
];
@NgModule({
  imports: [RouterModule.forRoot(routes),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {
}
