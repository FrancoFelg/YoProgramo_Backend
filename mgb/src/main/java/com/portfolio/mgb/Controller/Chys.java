package com.portfolio.mgb.Controller;


import com.portfolio.mgb.Entity.hys;
import com.portfolio.mgb.DTO.dtoHys;
import com.portfolio.mgb.Security.Controller.Mensaje;
import com.portfolio.mgb.Service.Shys;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/skill")
public class Chys {

    @Autowired
    private Shys shys;

    @GetMapping("/lista")
    public ResponseEntity<List<hys>> list(){
        List<hys> list = shys.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<hys> getById(@PathVariable("id") int id){
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        hys hys = shys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys){
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(shys.existsByNombre(dtohys.getNombre()))
            return new ResponseEntity<>(new Mensaje("Esa hys existe"), HttpStatus.BAD_REQUEST);

        hys hys = new hys( dtohys.getNombre(), dtohys.getPorcentaje() );
        shys.save(hys);
        return new ResponseEntity<>(new Mensaje("hys agregada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtohys){
        if(!shys.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);

        if(shys.existsByNombre(dtohys.getNombre()) && shys.getByNombre(dtohys.getNombre()).get().getId()  != id )
            return new ResponseEntity<>(new Mensaje("Esa hys ya existe"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        hys hys = shys.getOne(id).get();
        hys.setNombre(dtohys.getNombre());
        hys.setPorcentaje(dtohys.getPorcentaje());

        shys.save(hys);

        return new ResponseEntity<>(new Mensaje("hys actualizada"), HttpStatus.OK);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") int id){
        if(!shys.existsById(id))
            return new ResponseEntity<>(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);

        shys.delete(id);

        return new ResponseEntity<>(new Mensaje("hys eliminada"), HttpStatus.OK);
    }

}
