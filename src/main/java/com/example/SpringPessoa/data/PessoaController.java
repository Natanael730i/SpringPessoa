package com.example.SpringPessoa.data;

import com.example.SpringPessoa.model.Pessoa;
import com.example.SpringPessoa.repositories.PersonRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);
    private PersonRepository personRepository;

    @PostMapping("/pessoa")
    public Pessoa postPerson(@RequestBody Pessoa pessoa){
        return personRepository.save(pessoa);
    }

    @GetMapping("/pessoa")
    public List<Pessoa> getPersons() {
        return personRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public Long deletePerson(@PathVariable ObjectId id) {
        return personRepository.delete(id);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Exception handleAllExceptions(RuntimeException e) {
        LOGGER.error("Internal server error.", e);
        return e;
    }

}
