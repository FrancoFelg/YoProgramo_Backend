package com.portfolio.mgb.Repository;

import com.portfolio.mgb.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {

    public Optional<Persona> findByNombre(String nombre);

    public boolean existsByNombre(String nombre);

}
