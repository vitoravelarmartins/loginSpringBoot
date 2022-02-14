package com.cadchekuser.login.controller.forrm;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Setter
@Getter
public class SigninForm {

    private String email;
    private String passwordKey;

    public UsernamePasswordAuthenticationToken converter(){
        return new UsernamePasswordAuthenticationToken(email,passwordKey);
    }
}
