package com.template.unitest.controllers;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.template.unitest.exceptions.ResourseNotFoundException;
import com.template.unitest.models.Name;
import com.template.unitest.pojo.RequestName;
import com.template.unitest.service.NameService;
import lombok.NonNull;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@RestController
@RequestMapping(value="/api/names",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class NameController {
    final
    NameService service;

    public NameController(NameService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Name> list(){
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Name> findById(
            @PathVariable("id") String id
    ){
        return  new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Name findByName(
            @PathVariable("name") String name
    ){
        return service.findByName(name);
    }

    @PostMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Name> createName(
        @RequestBody RequestName name
    ) {
        try {
            service.findById(name.getId());
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }catch(ResourseNotFoundException ex){
            service.save(name.toName());
            return  new ResponseEntity<Name>(service.saveAndFlush(name.toName()),
                    HttpStatus.CREATED
                    );
        }
    }

    @PutMapping(value="/",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Name putById(
                @RequestBody RequestName name
            ){
        Name update = service.findById(name.getId());
        BeanUtils.copyProperties(name.toName(), update);
        service.findById(name.getId());
        service.save(update);
        return update;
    }

    @PatchMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Name patch(
            @RequestBody @NonNull Name name
    ){
        Name update = service.findById(name.getId());
        if (name.getName() != null){
            update.setName(name.getName());
        }
        if (name.getSurname() != null){
            update.setName(name.getSurname());
        }
        service.save(update);
        return update;
    }

    @DeleteMapping("/id/{id}")
    public void delete(
            @PathVariable("id") String id
    ){
        service.delete(id);
    }

}
