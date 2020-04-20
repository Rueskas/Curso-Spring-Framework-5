import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {
  public cliente: Cliente;
  constructor(
    private _clienteService: ClienteService,
    private _router: Router,
    private _activatedRoute: ActivatedRoute) {
    this.cliente = new Cliente();
  }

  ngOnInit(): void {
    this._activatedRoute.params.subscribe(
      params => {
        if (params['id']) {
          this._clienteService.getCliente(params['id']).subscribe(c => this.cliente = c);
        }
      }
    )
  }

  public send(): void {
    if (this.cliente.id) {
      this._clienteService.putCliente(this.cliente).subscribe(res => {
        Swal.fire('Cliente Modificado', `El cliente ${res.nombre} se ha modificado con éxito.`, 'success');
        this._router.navigate(['/clientes']);
      })
    } else {
      this._clienteService.postCliente(this.cliente).subscribe(res => {
        Swal.fire('Cliente Creado', `El cliente ${res.nombre} se ha creado con éxito.`, 'success');
        this._router.navigate(['/clientes']);
      })
    }
  }

}
