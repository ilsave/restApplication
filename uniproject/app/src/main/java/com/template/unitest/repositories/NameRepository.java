package com.template.unitest.repositories;

import com.template.unitest.models.Name;
import lombok.NonNull;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NameRepository extends MongoRepository<Name, String> {

    Optional<Name> findById(@NonNull String id);

    Name findByName(@NonNull String name);

    Name findBySurname(@NonNull String surname);

}
