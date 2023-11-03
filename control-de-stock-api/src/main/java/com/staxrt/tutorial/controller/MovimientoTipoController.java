package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.MovimientoTipo;
import com.staxrt.tutorial.service.MovimientoTipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class MovimientoTipoController {

    @Autowired
    private MovimientoTipoService movimientoTipoService;

    @GetMapping("/movimientos_tipos")
    public List<MovimientoTipo> getAllMovimientoTipos() {
        return movimientoTipoService.getAllMovimientoTipos();
    }

    @GetMapping("/movimientos_tipos/{id}")
    public ResponseEntity<MovimientoTipo> getMovimientoTipoById(@PathVariable(value = "id") Long movimientoTipoId) {
        MovimientoTipo movimientoTipo = movimientoTipoService.getMovimientoTipoById(movimientoTipoId);
        return ResponseEntity.ok().body(movimientoTipo);
    }

    @PostMapping("/movimientos_tipos")
    public MovimientoTipo createMovimientoTipo(@Valid @RequestBody MovimientoTipo movimientoTipo) {
        return movimientoTipoService.createMovimientoTipo(movimientoTipo);
    }

    @PutMapping("/movimientos_tipos/{id}")
    public ResponseEntity<MovimientoTipo> updateMovimientoTipo(
            @PathVariable(value = "id") Long movimientoTipoId, @Valid @RequestBody MovimientoTipo movimientoTipoDetails) {
        MovimientoTipo updatedMovimientoTipo = movimientoTipoService.updateMovimientoTipo(movimientoTipoId, movimientoTipoDetails);
        return ResponseEntity.ok(updatedMovimientoTipo);
    }
}
