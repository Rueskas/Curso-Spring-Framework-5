import { Component, OnInit } from '@angular/core';
import { Cliente } from './cliente';
import { ClienteService } from './cliente.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html'
})
export class ClientesComponent implements OnInit {
  clientes: Cliente[];
  constructor(private _clienteService: ClienteService) { }

  ngOnInit(): void {
    this._clienteService.getClientes()
      .subscribe(res => this.clientes = res);
  }

  public delete(id: number): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: "Una vez eliminado no se podrá recuperar",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.value) {
        this._clienteService.deleteCliente(id)
          .subscribe(_ => {
            this.clientes = this.clientes.filter(c => c.id != id);
            Swal.fire(
              'Eliminado',
              'El Cliente se ha eliminado.',
              'success'
            )
          });
      } else {
        Swal.fire(
          'Cancelado',
          'El Cliente no se ha eliminado.',
          'info'
        )
      }
    })
  }

}
