import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-directiva',
  templateUrl: './directiva.component.html'
})
export class DirectivaComponent implements OnInit {
  mostrando: boolean = true;
  listaCursos: string[] = ['C#', 'JavaScript', 'TypeScrpt', ' Java', 'Go', 'Python'];
  constructor() { }

  ngOnInit(): void {
  }

  public mostrar() {
    this.mostrando = true;
  }

  public ocultar() {
    this.mostrando = false;
  }

}
