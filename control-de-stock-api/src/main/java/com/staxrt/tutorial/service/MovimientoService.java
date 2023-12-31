package com.staxrt.tutorial.service;

import com.staxrt.tutorial.exception.ResourceNotFoundException;
import com.staxrt.tutorial.model.Movimiento;
import com.staxrt.tutorial.model.MovimientoTipo;
import com.staxrt.tutorial.model.ProductoDeposito;
import com.staxrt.tutorial.model.Producto;
import com.staxrt.tutorial.repository.MovimientoRepository;
import com.staxrt.tutorial.repository.MovimientoTipoRepository;
import com.staxrt.tutorial.repository.ProductoDepositoRepository;
import com.staxrt.tutorial.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.transaction.Transactional;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private MovimientoTipoRepository MovimientoTipoRepository;

    @Autowired
    private ProductoDepositoRepository productoDepositoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoDepositoService productoDepositoService;

    @Autowired
    private ProductoService productoService;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento getMovimientoById(Long movimientoId) {
        return movimientoRepository
                .findById(movimientoId)
                .orElseThrow(() -> new ResourceNotFoundException("movimiento not found on :: " + movimientoId));
    }

    @Transactional
    public Movimiento createMovimiento(Movimiento movimiento) {
        Producto producto = productoRepository.findById(movimiento.getProducto().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Producto not found with ID: " + movimiento.getProducto().getId()));

        ProductoDeposito productoDeposito = productoDepositoRepository.findByProductoAndDeposito(producto,
                movimiento.getDeposito());
        MovimientoTipo movimientoTipo = MovimientoTipoRepository.findById(movimiento.getMovimientoTipo().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MovimientoTipo not found with ID: " + movimiento.getMovimientoTipo().getId()));

        Integer nuevaCantidad = movimiento.getCantidad();
        Integer nuevaCantidadProducto = nuevaCantidad;

        if ("ACREEDOR".equals(movimientoTipo.getSaldo().toString())) {
            nuevaCantidad += productoDeposito.getCantidad();
            nuevaCantidadProducto += producto.getCantidad();
        } else {
            if(productoDeposito.getCantidad() >= nuevaCantidad){
                nuevaCantidad = productoDeposito.getCantidad() - nuevaCantidad;
                nuevaCantidadProducto = producto.getCantidad() - nuevaCantidadProducto;
            }
            else{
                throw new RuntimeException("Estás queriendo sacar " + nuevaCantidad + " y en el deposito hay " + productoDeposito.getCantidad());
            }
        }

        productoDepositoService.updateCantidadProductoDeposito(productoDeposito.getId(), nuevaCantidad);
        productoService.updateCantidadProducto(movimiento.getProducto().getId(), nuevaCantidadProducto);

        return movimientoRepository.save(movimiento);
    }

}