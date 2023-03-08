package com.portfolio.mgb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.mgb.Entity.hys;

import java.util.Optional;

public interface Rhys extends JpaRepository<hys, Integer> {

    Optional<hys> findByNombre(String nombre);

    public boolean existsByNombre(String nombre);

}
