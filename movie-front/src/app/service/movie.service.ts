import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Filme } from '../model/filme.model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public getAll(): Observable<Filme[]> {
    return this.http.get<Filme[]>(this.baseUrl + 'filmes');
  }

  public getById(id: number): Observable<Filme> {
    return this.http.get<Filme>(this.baseUrl + `filmes/${id}`);
  }

  public getByGenero(genero: string): Observable<Filme[]> {
    return this.http.get<Filme[]>(this.baseUrl + `genero/${genero}`)
  }

  public getByNome(nome: string): Observable<Filme[]> {
    return this.http.get<Filme[]>(this.baseUrl + `filmes/nome/${nome}`)
  }

  public create(filme: Filme): Observable<Filme> {
    return this.http.post<Filme>(this.baseUrl + 'filmes', filme);
  }

  public update(id: number, filme: Filme): Observable<Filme> {
    return this.http.put<Filme>(this.baseUrl + `filmes/${id}`, filme);
  }

  public delete(id: number): Observable<any> {
    return this.http.delete<any>(this.baseUrl + `filmes/${id}`);
}

}
