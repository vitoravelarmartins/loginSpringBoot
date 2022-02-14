package com.cadchekuser.login.controller;

import com.cadchekuser.login.controller.config.security.TokenService;
import com.cadchekuser.login.controller.dto.TokenDto;
import com.cadchekuser.login.controller.forrm.SigninForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/signin")
public class SigninController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping
    public ResponseEntity<TokenDto> auth(@RequestBody @Valid SigninForm signinForm){
        UsernamePasswordAuthenticationToken dataSignin = signinForm.converter();
      try {
          Authentication authentication = authenticationManager.authenticate(dataSignin);
          String token = tokenService.generateToken(authentication);
          System.out.println(token);

          return ResponseEntity.ok(new TokenDto(token, "Bearer"));

      }catch (AuthenticationException e){
          System.out.println("Error "+e);
          return ResponseEntity.badRequest().build();
      }

    }
}
