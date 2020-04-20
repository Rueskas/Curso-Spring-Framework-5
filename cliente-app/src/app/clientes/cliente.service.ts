import { Injectable } from '@angular/core';
//import { CLIENTES } from './Clientes.json';
import { Cliente } from './cliente';
import { of, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private _urlEndpoint: string = 'http://localhost:8080/api/clientes';
  constructor(private _http: HttpClient) { }

  public getClientes(): Observable<Cliente[]> {
    //return of(CLIENTES);
    return this._http.get<Cliente[]>(this._urlEndpoint);
  }
}
