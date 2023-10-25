package com.staxrt.tutorial.service;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Producto;
import com.staxrt.tutorial.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Long productoId) {
        return productoRepository
                .findById(productoId)
                .orElseThrow(() -> new ResourceNotFoundException("producto not found on :: " + productoId));
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long productoId, Producto productoDetails) {
        Producto producto = getProductoById(productoId);
        producto.setNombre(productoDetails.getNombre());
        producto.setDescripcion(productoDetails.getDescripcion());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setCantidad(productoDetails.getCantidad());
        return productoRepository.save(producto);
    }

    public Producto updateCantidadProducto(Long productoId, Integer nuevaCantidad) {
        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Producto not found on :: " + productoId));
            producto.setCantidad(nuevaCantidad);
        return productoRepository.save(producto);
    }
}