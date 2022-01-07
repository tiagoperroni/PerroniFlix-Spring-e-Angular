import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Filme } from 'src/app/model/filme.model';
import { Genero } from 'src/app/model/genero.model';
import { GeneroService } from 'src/app/service/genero.service';
import { MovieService } from 'src/app/service/movie.service';

@Component({
  selector: 'app-movie-update',
  templateUrl: './movie-update.component.html',
  styleUrls: ['./movie-update.component.css']
})
export class MovieUpdateComponent implements OnInit {

  genero: Genero = new Genero();
  generos: Genero[] = [];
  filme: Filme = new Filme();
  id!: number

  constructor(private service: MovieService, private route: ActivatedRoute,
     private generoService: GeneroService, private router: Router, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.findById();
    this.getAllGeneros();
  }

  findById(): void {    
    this.id = this.route.snapshot.params['id'];
    this.service.getById(this.id).subscribe((res) => {     
      this.filme = res;
      this.filme.genero = null!
    }, err => console.log(err));
  }

  atualizar(): void {    
    this.filme.genero = this.genero;
    this.service.update(this.id, this.filme).subscribe((res) => {  
      this.toastr.info("Filme atualizado com sucesso", "", {
        timeOut: 3000,     
      });
      this.router.navigate(['/']);
    }, err => {
      if (err.error.status === 400 ) {
        this.toastr.error("Ops, selecione o gênero.", "", {
          timeOut: 3000
        });
      } else {
        this.toastr.error("Ops.. Houve um erro ao atualizar.", "", {
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
