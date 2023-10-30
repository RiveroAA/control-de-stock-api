import { Component } from '@angular/core';
import { MovimientosService } from 'src/app/services/movimientos/movimientos.service';

// interface Movimiento {
//   id: number;
//   cantidad: number;
//   producto: {
//     id: number;
//     nombre: string;
//     descripcion: string;
//     precio: number;
//     cantidad: number;
//     fechaActualizacion: string;
//     fechaCreacion: string;
//   };
//   movimientoTipo: {
//     id: number;
//     nombre: string;
//     descripcion: string;
//     saldo: string;
//   };
//   deposito: {
//     id: number;
//     nombre: string;
//   };
//   fecha: string;
// }

@Component({
  selector: 'app-movimientos',
  templateUrl: './movimientos.component.html',
  styleUrls: ['./movimientos.component.css']
})
export class MovimientosComponent {
  movimientos: any;

  constructor(private movimientosService: MovimientosService) {}

  ngOnInit() {
    this.movimientosService.pedirMovimientos().subscribe(data => {
      this.movimientos = data;
      console.log(this.movimientos);
    });
  }
}
