package com.template.unitest.service;


import com.template.unitest.exceptions.ResourseNotFoundException;
import com.template.unitest.models.Name;
import com.template.unitest.repositories.NameRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NameService {

    final
    NameRepository repository;

    public NameService(NameRepository repository) {
        this.repository = repository;
    }

    public Name findById(@NonNull String id) throws  ResourseNotFoundException{
        return repository.findById(id).orElseThrow(ResourseNotFoundException::new);
    }

    public Name findBySurname(@NonNull String surname){
        return repository.findBySurname(surname);
    }

    public Name findByName(@NonNull String name){
        return repository.findByName(name);
    }

    public List<Name> getAll(){
        return repository.findAll();
    }


    public void save(@NonNull Name name) {
        repository.save(name);
    }

    public Name saveAndFlush(@NonNull Name name){
        save(name);
        return name;
    }

    public void delete(@NonNull String id){
        repository.deleteById(id);
    }
}
