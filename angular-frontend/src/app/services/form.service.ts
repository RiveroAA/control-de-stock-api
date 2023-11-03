
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  constructor(private http: HttpClient) { }

  pedirMovimientos() {
    return this.http.get<any[]>('http://localhost:8080/api/stock/movimientos');
  }

  pedirProductos() {
    return this.http.get<any[]>('http://localhost:8080/api/stock/productos');
  }

  pedirDepositos() {
    return this.http.get<any[]>('http://localhost:8080/api/stock/depositos');
  }

  peditTiposMovimientos() {
    return this.http.get<any[]>('http://localhost:8080/api/stock/movimientos_tipos');
  }

  pedirProductoEspecifico(id: string) {
    return this.http.get<any[]>('http://localhost:8080/api/stock/productos/' + id);
  }

  pedirCantidadProductoEspecifico(id: string) {
    return this.http.get<any[]>('http://localhost:8080/api/stock/productos_depositos/producto/' + id);
  }

  crearMovimiento(nuevoMovimiento: any) {
    return this.http.post<any>('http://localhost:8080/api/stock/movimientos', nuevoMovimiento);
  }
}
