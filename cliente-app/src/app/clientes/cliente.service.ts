import { Injectable } from '@angular/core';
//import { CLIENTES } from './Clientes.json';
import { Cliente } from './cliente';
import { of, Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  private _urlEndpoint: string = 'http://localhost:8080/api/clientes';
  private _httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })
  constructor(private _http: HttpClient, private _roter: Router) { }

  public getClientes(): Observable<Cliente[]> {
    //return of(CLIENTES);
    return this._http.get<Cliente[]>(this._urlEndpoint);
  }

  public getCliente(id: number): Observable<Cliente> {
    //return of(CLIENTES);
    return this._http.get<Cliente>(this._urlEndpoint + '/' + id).pipe(
      catchError(e => {
        let titulo: string;
        let contenido: string;
        if (e.status == 404) {
          titulo = 'Cliente no encontrado';
          contenido = 'No se ha encontrado el cliente con ID:' + id;
        } else if (e.status == 500) {
          titulo = 'Error';
          contenido = 'No se ha podido conectar a la base de datos, inténtelo más tarde.';
        } else {
          titulo = 'Error';
          contenido = 'Ha ocurrido un error inesperado, inténtelo más tarde.';
        }
        Swal.fire(titulo, contenido, 'error');
        this._roter.navigate(['/clientes']);
        return throwError(e);
      })
    );
  }

  public postCliente(cliente: Cliente): Observable<Cliente> {
    return this._http.post<Cliente>(this._urlEndpoint, cliente, { headers: this._httpHeaders }).pipe(
      catchError(e => {
        let titulo: string;
        let contenido: string;
        if (e.status == 400) {
          return throwError(e);
        } else if (e.status == 500) {
          titulo = 'Error';
          contenido = 'No se ha podido conectar a la base de datos, inténtelo más tarde.';
        } else {
          titulo = 'Error';
          contenido = 'Ha ocurrido un error inesperado, inténtelo más tarde.';
        }
        Swal.fire(titulo, contenido, 'error');
        return throwError(e);
      })
    );
  }

  public putCliente(cliente: Cliente): Observable<Cliente> {
    return this._http.put<Cliente>(this._urlEndpoint + '/' + cliente.id, cliente,
      { headers: this._httpHeaders }).pipe(
        catchError(e => {
          let titulo: string;
          let contenido: string;
          if (e.status == 400) {
            return throwError(e);
          } else if (e.status == 404) {
            titulo = 'Cliente no encontrado';
            contenido = 'No se ha encontrado el cliente con ID:' + cliente.id;
          } else if (e.status == 500) {
            titulo = 'Error';
            contenido = 'No se ha podido conectar a la base de datos, inténtelo más tarde.';
          } else {
            titulo = 'Error';
            contenido = 'Ha ocurrido un error inesperado, inténtelo más tarde.';
          }
          Swal.fire(titulo, contenido, 'error');
          return throwError(e);
        })
      );
  }

  public deleteCliente(id: number): Observable<any> {
    return this._http.delete<Cliente>(this._urlEndpoint + '/' + id);
  }


}
