package com.template.unitest.pojo;

import com.template.unitest.models.Name;
import lombok.NonNull;
import lombok.Value;
import org.springframework.data.annotation.Id;

@Value
public class RequestName {


    @NonNull private String id;

    @NonNull private String name;

    @NonNull private String surname;


    public Name toName() {
        return new Name(id, name, surname);
    }
}
