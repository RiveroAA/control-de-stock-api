import { Component } from '@angular/core';
import { FormService } from 'src/app/services/form.service';

interface DatosFormulario {
  cantidad: number;
  producto: number;
  movimientoTipo: number;
  deposito: number;
}

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent {
  formularioData: DatosFormulario = { cantidad: 0, producto: 0, movimientoTipo: 0, deposito: 0 };
  movimientos: any;
  productos: any;
  depositos: any;
  tipos_movimientos: any;
  productoEspecifico: any
  depositoProductoEspecifico: any;

  constructor(private formService: FormService) {}

  ngOnInit() {
    this.mostrarMovimientos();
    this.mostrarProductos();
    this.mostrarDepositos();
    this.mostrarTiposMovimientos();
  }

  mostrarMovimientos() {
    this.formService.pedirMovimientos().subscribe(data => {
      this.movimientos = data;
      this.movimientos.reverse();
    });
  }

  mostrarProductos() {
    this.formService.pedirProductos().subscribe(data => {
      this.productos = data;
    })
  }

  mostrarDepositos() {
    this.formService.pedirDepositos().subscribe(data => {
      this.depositos = data;
    })
  }

  mostrarTiposMovimientos() {
    this.formService.peditTiposMovimientos().subscribe(data => {
      this.tipos_movimientos = data;
    })
  }

  getSelectedProduct(event: Event) {
    const selectElement = event.target as HTMLSelectElement;
    const selectedProducto = selectElement.value;
    console.log('Producto seleccionado:', selectedProducto);

    if (selectedProducto) {
      this.formService.pedirProductoEspecifico(selectedProducto).subscribe((data) => {
        this.productoEspecifico = data;
        console.log('Cantidad', this.productoEspecifico.cantidad);
      });
      this.formService.pedirCantidadProductoEspecifico(selectedProducto).subscribe((data) => {
        this.depositoProductoEspecifico = data;
        console.log(this.depositoProductoEspecifico);
      });
    }
  }

  onSubmitForm(formData: any) {
    const nuevoMovimiento = {
      cantidad: this.formularioData['cantidad'],
      producto: { id: this.formularioData['producto'] },
      movimientoTipo: { id: this.formularioData['movimientoTipo'] },
      deposito: { id: this.formularioData['deposito'] }
    };

    this.formService.crearMovimiento(nuevoMovimiento).subscribe(data => {
    });
  }
}
