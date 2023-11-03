package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.model.Deposito;
import com.staxrt.tutorial.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
@CrossOrigin(origins = "http://localhost:4200")
public class DepositoController {

    @Autowired
    private DepositoService depositoService;

    @GetMapping("/depositos")
    public List<Deposito> getAllDepositos() {
        return depositoService.getAllDepositos();
    }

    @GetMapping("/depositos/{id}")
    public ResponseEntity<Deposito> getDepositoById(@PathVariable(value = "id") Long depositoId) {
        Deposito deposito = depositoService.getDepositoById(depositoId);
        return ResponseEntity.ok().body(deposito);
    }

    @PostMapping("/depositos")
    public Deposito createDeposito(@Valid @RequestBody Deposito deposito) {
        return depositoService.createDeposito(deposito);
    }

    @PutMapping("/depositos/{id}")
    public ResponseEntity<Deposito> updateDeposito(
            @PathVariable(value = "id") Long depositoId, @Valid @RequestBody Deposito depositoDetails) {
        Deposito updatedDeposito = depositoService.updateDeposito(depositoId, depositoDetails);
        return ResponseEntity.ok(updatedDeposito);
    }
}
