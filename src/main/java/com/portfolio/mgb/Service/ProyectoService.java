package com.portfolio.mgb.Service;

import com.portfolio.mgb.Entity.Proyecto;
import com.portfolio.mgb.Repository.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProyectoService {


    @Autowired
    private ProyectoRepository proyectoRepository;

    public List<Proyecto> list(){
        return proyectoRepository.findAll();
    }

    public Optional<Proyecto> getOne(int id){
        return proyectoRepository.findById(id);
    }

    public Optional<Proyecto> getByNombreE(String nombreE){
        return proyectoRepository.findByNombre(nombreE);
    }

    public void save(Proyecto expe){
        proyectoRepository.save(expe);
    }

    public void delete(int id){
        proyectoRepository.deleteById(id);
    }

    public boolean existsByid(int id){
        return proyectoRepository.existsById(id);
    }

    public boolean existsByNombreE(String nombreE){
        return proyectoRepository.existsByNombre(nombreE);
    }

}
