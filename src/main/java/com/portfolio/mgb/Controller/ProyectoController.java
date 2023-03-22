package com.portfolio.mgb.Controller;

import com.portfolio.mgb.Entity.Proyecto;
import com.portfolio.mgb.Security.Controller.Mensaje;
import com.portfolio.mgb.DTO.dtoProyecto;
import com.portfolio.mgb.Service.ProyectoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("proyectos")
@CrossOrigin(origins = "https://portfolio-frontend-argprog.web.app")
public class ProyectoController {


    @Autowired
    ProyectoService proyectoService;

    @GetMapping("/lista")
    public ResponseEntity<List<Proyecto>> list(){
        List<Proyecto> list = proyectoService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyecto> getById(@PathVariable("id") int id){
        if(!proyectoService.existsByid(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Proyecto Proyecto = proyectoService.getOne(id).get();
        return new ResponseEntity(Proyecto, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoProyecto dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(proyectoService.existsByNombreE(dtoexp.getNombre()))
            return new ResponseEntity<>(new Mensaje("Ese Proyecto existe"), HttpStatus.BAD_REQUEST);

        Proyecto Proyecto = new Proyecto( dtoexp.getNombre(), dtoexp.getDescripcion() );
        proyectoService.save(Proyecto);
        return new ResponseEntity<>(new Mensaje("Proyecto agregado"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoProyecto dtoexp){
        if(!proyectoService.existsByid(id))
            return new ResponseEntity<>(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);

        if(proyectoService.existsByNombreE(dtoexp.getNombre()) && proyectoService.getByNombreE(dtoexp.getNombre()).get().getId()  != id )
            return new ResponseEntity<>(new Mensaje("Ese Proyecto ya existe"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(dtoexp.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        Proyecto Proyecto = proyectoService.getOne(id).get();
        Proyecto.setNombre(dtoexp.getNombre());
        Proyecto.setDescripcion(dtoexp.getDescripcion());

        proyectoService.save(Proyecto);

        return new ResponseEntity<>(new Mensaje("Proyecto actualizado"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") int id){
        if(!proyectoService.existsByid(id))
            return new ResponseEntity<>(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);

        proyectoService.delete(id);

        return new ResponseEntity<>(new Mensaje("Proyecto eliminado"), HttpStatus.OK);
    }

}
