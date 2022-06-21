package com.axonactive.personalproject.security.jwt;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class JwtResponse implements Serializable {
  private String token;
  private String type = "Bearer";
  private String username;
  private List<String> roles;

  public JwtResponse(String token, String username, List<String> roles) {
    this.token = token;
    this.username = username;
    this.roles = roles;
  }
}
