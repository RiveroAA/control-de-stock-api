package com.staxrt.tutorial.controller;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Producto;
import com.staxrt.tutorial.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class ProductoController {

  @Autowired
    private ProductoService productoService;

    @GetMapping("/productos")
    public List<Producto> getAllProductos() {
        return productoService.getAllProductos();
    }

    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable(value = "id") Long productoId)
            throws ResourceNotFoundException {
        Producto producto = productoService.getProductoById(productoId);
        return ResponseEntity.ok().body(producto);
    }

    @PostMapping("/productos")
    public Producto createProducto(@Valid @RequestBody Producto producto) {
        return productoService.createProducto(producto);
    }

    @PutMapping("/productos/{id}")
    public ResponseEntity<Producto> updateProducto(
            @PathVariable(value = "id") Long productoId,
            @Valid @RequestBody Producto productoDetails)
            throws ResourceNotFoundException {
        Producto updatedProducto = productoService.updateProducto(productoId, productoDetails);
        return ResponseEntity.ok(updatedProducto);
    }
}