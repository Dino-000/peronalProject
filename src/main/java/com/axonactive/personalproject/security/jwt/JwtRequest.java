package com.axonactive.personalproject.security.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor    //need default constructor for JSON Parsing
@AllArgsConstructor
public class JwtRequest implements Serializable {
    private String username;
    private String password;
}
