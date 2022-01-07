import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieCreateComponent } from './components/movie-create/movie-create.component';
import { MovieDeleteComponent } from './components/movie-delete/movie-delete.component';
import { MovieDescriptionComponent } from './components/movie-description/movie-description.component';
import { MovieReadComponent } from './components/movie-read/movie-read.component';
import { MovieUpdateComponent } from './components/movie-update/movie-update.component';

const routes: Routes = [
  { path: '', component: MovieReadComponent },
  { path: 'details/:id', component: MovieDescriptionComponent },
  { path: 'create', component: MovieCreateComponent },
  { path: 'update/:id', component: MovieUpdateComponent },
  { path: 'delete/:id', component: MovieDeleteComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
