import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Filme } from 'src/app/model/filme.model';
import { MovieService } from 'src/app/service/movie.service';

@Component({
  selector: 'app-movie-read',
  templateUrl: './movie-read.component.html',
  styleUrls: ['./movie-read.component.css']
})
export class MovieReadComponent implements OnInit {
  
  queryField = new FormControl();

  public page = 1;
  public pageSize = 8;

  filmes: Filme[] = [];
  filme: Filme = new Filme();

  constructor(private service: MovieService) { }

  ngOnInit(): void {
    this.getAll();  
  }

  getAll(): Filme[] { 
     
    this.service.getAll().subscribe((res) => {
      this.filmes = res;
    }, err => console.log(err));
    return this.filmes;
  }

  buscarPorNome(): void {
    console.log(this.filme.nome)
    if (this.filme.nome === undefined) {
      this.filmes = this.getAll();
    } else { 
      this.service.getByNome(this.filme.nome).subscribe((res) => {
      this.filmes = res;
      this.filme.nome = undefined!
      }, err => console.log(err));     
    }   
  }

}
