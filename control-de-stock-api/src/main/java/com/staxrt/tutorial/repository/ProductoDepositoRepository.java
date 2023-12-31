package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.ProductoDeposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.staxrt.tutorial.model.Producto;
import com.staxrt.tutorial.model.Deposito;

import java.util.List;


@Repository
public interface ProductoDepositoRepository extends JpaRepository<ProductoDeposito, Long> {
    List<ProductoDeposito> findByProductoId(Long productoId);
    ProductoDeposito findByProductoAndDeposito(Producto producto, Deposito deposito);
}