package com.cadchekuser.login.controller.config.security;

import com.cadchekuser.login.model.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


import java.util.Date;

@Service
public class TokenService {

    @Value("${person.jwt.expiration}")
    private String expiration;

    @Value("${person.jwt.secret}")
    private String secret;

    public String generateToken (Authentication authentication){
        Person logged = (Person) authentication.getPrincipal();
        Date nowDate = new Date();
        Date dataExpiration = new Date(nowDate.getTime()+Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API LOGIN VITOR")
                .setSubject(logged.getId().toString())
                .setIssuedAt(nowDate)
                .setExpiration(dataExpiration)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();


    }

    public boolean isTokenValidated(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            return false;
        }

    }

public Long getIdPerson(String token){
    Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    System.out.println(claims);
    return Long.parseLong(claims.getSubject());
}

}
