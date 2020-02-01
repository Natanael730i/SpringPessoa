package com.example.SpringPessoa.repositories;

import com.example.SpringPessoa.model.Pessoa;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface PersonRepository {

    Pessoa save(Pessoa person);

    List<Pessoa> findAll();

    long delete(ObjectId id);

}
