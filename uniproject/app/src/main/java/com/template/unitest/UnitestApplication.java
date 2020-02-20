package com.template.unitest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.template.unitest.models.Name;
import com.template.unitest.repositories.NameRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class UnitestApplication implements CommandLineRunner {

    final
    NameRepository nameRepository;

    public UnitestApplication(NameRepository nameRepository) {
        this.nameRepository = nameRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(UnitestApplication.class, args);
    }

    @Override
    @SneakyThrows
    public void run(String... args) {
        System.out.println("/n/n/n Data overriting");
        nameRepository.deleteAll();
        nameRepository.saveAll(List.of(
                    new ObjectMapper().readValue(
                            "[{\"id\":\"0\",\"name\":\"Ilya\",\"surname\":\"Gushchin\"},{\"id\":\"1\",\"name\":\"Honda\",\"surname\":\"Genius\"},{\"id\":\"2\",\"name\":\"Tesla\",\"surname\":\"Mask\"},{\"id\":\"3\",\"name\":\"China\",\"surname\":\"Coronavirus\"}]",
                            Name[].class
                    )
                )
        );
        nameRepository.findAll().forEach(System.out::println);

    }
}
