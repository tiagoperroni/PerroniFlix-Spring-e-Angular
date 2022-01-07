import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Filme } from 'src/app/model/filme.model';
import { Genero } from 'src/app/model/genero.model';
import { GeneroService } from 'src/app/service/genero.service';
import { MovieService } from 'src/app/service/movie.service';

@Component({
  selector: 'app-movie-create',
  templateUrl: './movie-create.component.html',
  styleUrls: ['./movie-create.component.css']
})
export class MovieCreateComponent implements OnInit {

  filme: Filme = new Filme;
  generos: Genero[] = [];
  genero: Genero = new Genero();

  constructor(private service: MovieService, private router: Router, 
    private generoService: GeneroService, private toastr: ToastrService) { }

  ngOnInit(): void {   
    this.getAllGeneros();
  }

  criar(): void {
    this.filme.genero = this.genero;
    this.service.create(this.filme).subscribe((res) => {
      this.toastr.info("Filme salvo com sucesso.", "", {
        timeOut: 3000
      });
      this.router.navigate(['/']);
    }, err => {
      if (err.error.status === 400 || err.error.statusCode === 400 ) {
        this.toastr.error("Ops.. Preencha todos os campos!", "", {
          timeOut: 3000
        });
      } else {
        this.toastr.error("Ops.. Houve um erro ao cadastrar.", "", {
          timeOut: 3000
        });
      }
      console.log(err)
    });
  }

  getAllGeneros(): void {
    this.generoService.getAll().subscribe((res) => {
      this.generos = res;
    }, err => console.log(err));
  }

}
