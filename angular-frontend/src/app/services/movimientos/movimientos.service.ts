import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MovimientosService {

  constructor(private http: HttpClient) { }

  pedirMovimientos() {
    return this.http.get<any[]>('http://localhost:8080/api/stock/movimientos');
  }
}
