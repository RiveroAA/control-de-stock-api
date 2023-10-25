package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.ProductoDeposito;
import com.staxrt.tutorial.service.ProductoDepositoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class ProductoDepositoController {

  @Autowired
    private ProductoDepositoService productoDepositoService;

    @GetMapping("/productos_depositos")
    public List<ProductoDeposito> getAllProductosDepositos() {
        return productoDepositoService.getAllProductosDepositos();
    }

    @GetMapping("/productos_depositos/{id}")
    public ResponseEntity<ProductoDeposito> getProductoDepositoById(@PathVariable(value = "id") Long productoDepositoId)
            throws ResourceNotFoundException {
        ProductoDeposito productoDeposito = productoDepositoService.getProductoDepositoById(productoDepositoId);
        return ResponseEntity.ok().body(productoDeposito);
    }

    @PostMapping("/productos_depositos")
    public ProductoDeposito createProductoDeposito(@Valid @RequestBody ProductoDeposito productoDeposito) {
        return productoDepositoService.createProductoDeposito(productoDeposito);
    }

    @PutMapping("/productos_depositos/{id}")
    public ResponseEntity<ProductoDeposito> updateProductoDeposito(
            @PathVariable(value = "id") Long productoDepositoId,
            @Valid @RequestBody ProductoDeposito productoDepositoDetails)
            throws ResourceNotFoundException {
        ProductoDeposito updatedProductoDeposito = productoDepositoService.updateProductoDeposito(productoDepositoId, productoDepositoDetails);
        return ResponseEntity.ok(updatedProductoDeposito);
    }
}