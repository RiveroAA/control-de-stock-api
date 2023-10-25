package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
        Producto findById(Producto producto);
}