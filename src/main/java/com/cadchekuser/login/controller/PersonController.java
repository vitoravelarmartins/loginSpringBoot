package com.cadchekuser.login.controller;

import com.cadchekuser.login.controller.dto.PersonDto;
import com.cadchekuser.login.controller.forrm.PersonForm;
import com.cadchekuser.login.model.Person;
import com.cadchekuser.login.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;


    @GetMapping
    public List<PersonDto> lstPerson(){
        List<Person> persons = personRepository.findAll();
        return Person.convert(persons);

    }

    @PostMapping
    @Transactional
    public ResponseEntity<PersonDto> register(@RequestBody @Valid PersonForm personForm,
                                              UriComponentsBuilder uriComponentsBuilder){

        Person person = personForm.convert();
        personRepository.save(person);
        URI uri = uriComponentsBuilder.path("/person/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).body(new PersonDto(person));
    }
}
