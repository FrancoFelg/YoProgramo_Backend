package com.portfolio.mgb.Repository;

import com.portfolio.mgb.Entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProyectoRepository extends JpaRepository<Proyecto, Integer> {

    public Optional<Proyecto> findByNombre(String nombre);

    public boolean existsByNombre(String nombre);

}
