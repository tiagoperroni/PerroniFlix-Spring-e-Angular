import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule} from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MovieReadComponent } from './components/movie-read/movie-read.component';
import { HeaderComponent } from './views/components/header/header.component';
import { FooterComponent } from './views/components/footer/footer.component';
import { MovieDescriptionComponent } from './components/movie-description/movie-description.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { MovieCreateComponent } from './components/movie-create/movie-create.component';
import { FormsModule } from '@angular/forms';
import { MovieUpdateComponent } from './components/movie-update/movie-update.component';
import { ToastrModule } from 'ngx-toastr';
import { MovieDeleteComponent } from './components/movie-delete/movie-delete.component';

@NgModule({
  declarations: [
    AppComponent,
    MovieReadComponent,
    HeaderComponent,
    FooterComponent,
    MovieDescriptionComponent,
    MovieCreateComponent,
    MovieUpdateComponent,
    MovieDeleteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, HttpClientModule, NgbModule, FormsModule, BrowserAnimationsModule, ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
