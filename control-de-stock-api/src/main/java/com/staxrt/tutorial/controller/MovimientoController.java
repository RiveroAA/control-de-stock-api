package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Movimiento;
import com.staxrt.tutorial.service.MovimientoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class MovimientoController {

  @Autowired
  private MovimientoService movimientoService;

  @GetMapping("/movimientos")
  public List<Movimiento> getAllMovimientos() {
      return movimientoService.getAllMovimientos();
  }
  
  @GetMapping("/movimientos/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable(value = "id") Long movimientoId)
            throws ResourceNotFoundException {
        Movimiento movimiento = movimientoService.getMovimientoById(movimientoId);
        return ResponseEntity.ok().body(movimiento);
  }

  @PostMapping("/movimientos")
    public Movimiento createMovimiento(@Valid @RequestBody Movimiento movimiento) {
      return movimientoService.createMovimiento(movimiento);
  }
}