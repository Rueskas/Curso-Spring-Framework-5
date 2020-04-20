import { Injectable } from '@angular/core';
//import { CLIENTES } from './Clientes.json';
import { Cliente } from './cliente';
import { of, Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private _urlEndpoint: string = 'http://localhost:8080/api/clientes';
  private _httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  constructor(private _http: HttpClient) { }

  public getClientes(): Observable<Cliente[]> {
    //return of(CLIENTES);
    return this._http.get<Cliente[]>(this._urlEndpoint);
  }

  public getCliente(id: number): Observable<Cliente> {
    //return of(CLIENTES);
    return this._http.get<Cliente>(this._urlEndpoint + '/' + id);
  }

  public postCliente(cliente: Cliente): Observable<Cliente> {
    return this._http.post<Cliente>(this._urlEndpoint, cliente, { headers: this._httpHeaders });
  }

  public putCliente(cliente: Cliente): Observable<Cliente> {
    return this._http.put<Cliente>(this._urlEndpoint + '/' + cliente.id, cliente, { headers: this._httpHeaders });
  }

  public deleteCliente(id: number): Observable<any> {
    return this._http.delete<Cliente>(this._urlEndpoint + '/' + id);
  }
}
