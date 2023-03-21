package com.portfolio.mgb.Controller;

import com.portfolio.mgb.DTO.dtoPersona;
import com.portfolio.mgb.Entity.Persona;
import com.portfolio.mgb.Security.Controller.Mensaje;
import com.portfolio.mgb.Service.ImpPersonaService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://portfolio-frontend-argprog.web.app")
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    ImpPersonaService personaService;

    @GetMapping("/lista")
    public ResponseEntity<List<Persona>> list(){
        List<Persona> list = personaService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!personaService.existsByid(id)){
            return new ResponseEntity<>(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        personaService.delete(id);
        return new ResponseEntity<>(new Mensaje("Educación elimninada"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id){
        if(!personaService.existsByid(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        }

        Persona Persona = personaService.getOne(id).get();
        return new ResponseEntity<>(Persona, HttpStatus.OK);

    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtoPersona){
        if(StringUtils.isBlank(dtoPersona.getNombre())){
            return new ResponseEntity<>(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if(personaService.existsByNombreE(dtoPersona.getNombre())){
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        Persona Persona = new Persona(dtoPersona.getNombre(), dtoPersona.getApellido(), dtoPersona.getDescripcion(), dtoPersona.getImg(), dtoPersona.getImgFondo());

        personaService.save(Persona);
        return new ResponseEntity<>(new Mensaje("Persona Creada"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody dtoPersona dtoPersona){
        if(!personaService.existsByid(id)){
            return new ResponseEntity<>(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        if(personaService.existsByNombreE(dtoPersona.getNombre()) && personaService.getByNombreE((dtoPersona.getNombre())).get().getId() != id ){
            return new ResponseEntity<>(new Mensaje("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(dtoPersona.getNombre())){
            return new ResponseEntity<>(new Mensaje("El campo no puede estar vacío"), HttpStatus.BAD_REQUEST);
        }

        Persona Persona = personaService.getOne(id).get();
        Persona.setNombre(dtoPersona.getNombre() );
        Persona.setApellido(dtoPersona.getApellido() );
        Persona.setDescripcion(dtoPersona.getDescripcion());
        Persona.setImg(dtoPersona.getImg() );
        Persona.setImgFondo(dtoPersona.getImgFondo() );



        personaService.save(Persona);
        return new ResponseEntity<>(new Mensaje("Educación actualizada"), HttpStatus.OK);

    }




}
