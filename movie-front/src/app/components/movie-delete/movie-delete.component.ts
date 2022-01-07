import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Filme } from 'src/app/model/filme.model';
import { Genero } from 'src/app/model/genero.model';
import { GeneroService } from 'src/app/service/genero.service';
import { MovieService } from 'src/app/service/movie.service';

@Component({
  selector: 'app-movie-delete',
  templateUrl: './movie-delete.component.html',
  styleUrls: ['./movie-delete.component.css']
})
export class MovieDeleteComponent implements OnInit {

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

  deletar(): void {    
    this.filme.genero = this.genero;
    this.service.delete(this.id).subscribe((res) => {  
      this.toastr.info("Filme deletado com sucesso", "", {
        timeOut: 3000,     
      });
      this.router.navigate(['/']);
    }, err => console.log(err));
  }

  getAllGeneros(): void {
    this.generoService.getAll().subscribe((res) => {    
      this.generos = res;
    }, err => console.log(err));
  }

}
