import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Filme } from 'src/app/model/filme.model';
import { MovieService } from 'src/app/service/movie.service';

@Component({
  selector: 'app-movie-description',
  templateUrl: './movie-description.component.html',
  styleUrls: ['./movie-description.component.css']
})
export class MovieDescriptionComponent implements OnInit {

  filme: Filme = new Filme;
  id!: number;

  constructor(private service: MovieService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getById();
  }

  getById(): void {
    this.id = this.route.snapshot.params["id"];
    this.service.getById(this.id).subscribe((res) => {
      this.filme = res;
    }, err => console.log(err));
  }

}
