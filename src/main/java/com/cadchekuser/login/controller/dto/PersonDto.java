package com.cadchekuser.login.controller.dto;


import com.cadchekuser.login.model.Person;
import lombok.Getter;

@Getter
public class PersonDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;

    public PersonDto(Person person) {
        this.id = person.getId();
        this.firstName = person.getFirstName();
        this.lastName = person.getLastName();
        this.email = person.getEmail();
        this.cpf = person.getCpf();
    }
}
