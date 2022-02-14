package com.cadchekuser.login.model;

import com.cadchekuser.login.controller.dto.PersonDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
public class Person implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String cpf;
    @Enumerated(EnumType.STRING)
    private StatusPerson status = StatusPerson.ENABLE;
    @JsonIgnore
    private String passwordkey;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ProfileTypes> profileTypes = new ArrayList<>();

    public Person() {
    }

    public Person(String firstName, String lastName, String email, String cpf, String passwordkey) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cpf= cpf;
        this.passwordkey =passwordkey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id.equals(person.id) && firstName.equals(person.firstName) && lastName.equals(person.lastName) && email.equals(person.email) && cpf.equals(person.cpf) && status == person.status && passwordkey.equals(person.passwordkey);
    }

    public static List<PersonDto> convert(List<Person> personList){
        return personList.stream().map(PersonDto::new).collect(Collectors.toList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, cpf, status, passwordkey);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.profileTypes;
    }

    @Override
    public String getPassword() {
        return this.passwordkey;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
