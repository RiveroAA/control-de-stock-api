package com.staxrt.tutorial.repository;

import com.staxrt.tutorial.model.MovimientoTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoTipoRepository extends JpaRepository<MovimientoTipo, Long> {}