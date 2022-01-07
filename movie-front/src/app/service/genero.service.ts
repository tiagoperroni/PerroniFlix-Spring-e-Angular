import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Genero } from '../model/genero.model';

@Injectable({
  providedIn: 'root'
})
export class GeneroService {

  baseUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

public getAll(): Observable<Genero[]> {
  return this.http.get<Genero[]>(this.baseUrl + '/generos')
}

}
