import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './components/user/user.component';
import { ProjectComponent } from './modules/project/project.component';
import { ComplexityComponent } from './components/complexity/complexity.component';
import { EntryComponent } from './components/entry/entry.component';
import { ResourceComponent } from './components/resource/resource.component';
import { TagComponent } from './components/tag/tag.component';
// update User
import { UpdateUserComponent } from './components/user/update-user/update-user.component';

const routes: Routes = [
  // lazy load for ProjectModule
  {
    path: 'project',
    loadChildren: () =>
      import('./modules/project/project.module').then((m) => m.ProjectModule),
  },
  { path: 'users', component: UserComponent },
  { path: 'projects', component: ProjectComponent },
  { path: 'complexities', component: ComplexityComponent },
  { path: 'entries', component: EntryComponent },
  { path: 'resources', component: ResourceComponent },
  { path: 'tags', component: TagComponent },
  // update User  /user/update/{id}
  { path: 'user/update/:id', component: UpdateUserComponent },
  { path: 'project', loadChildren: () => import('./modules/project/project.module').then(m => m.ProjectModule) },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
