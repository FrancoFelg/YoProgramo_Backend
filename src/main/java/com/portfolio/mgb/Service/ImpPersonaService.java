package com.portfolio.mgb.Service;

import com.portfolio.mgb.Entity.Persona;
import com.portfolio.mgb.Repository.IPersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ImpPersonaService  {

    @Autowired
    IPersonaRepository iPersonaRepository;

    public List<Persona> list(){
        return iPersonaRepository.findAll();
    }

    public Optional<Persona> getOne(int id){
        return iPersonaRepository.findById(id);
    }

    public Optional<Persona> getByNombreE(String nombreE){
        return iPersonaRepository.findByNombre(nombreE);
    }

    public void save(Persona Persona){
        iPersonaRepository.save(Persona);
    }

    public void delete(int id){
        iPersonaRepository.deleteById(id);
    }

    public boolean existsByid(int id){
        return iPersonaRepository.existsById(id);
    }

    public boolean existsByNombreE(String nombreE){
        return iPersonaRepository.existsByNombre(nombreE);
    }

}
