package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.exception.UnauthorizedAccessException;
import com.axonactive.personalproject.service.UserAccountService;
import com.axonactive.personalproject.service.security.JwtAuthenticationService;
import com.axonactive.personalproject.service.security.Token;
import com.axonactive.personalproject.service.security.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(UserAccountResource.PATH)
public class UserAccountResource {
  public static final String PATH = "/api/useraccounts";
  @Autowired UserAccountService userAccountService;
  @Autowired JwtAuthenticationService jwtAuthenticationService;

  @GetMapping
  public ResponseEntity<List<UserAccount>> getAll() {
    return ResponseEntity.ok().body(userAccountService.getAll());
  }

  @GetMapping("/{userName}")
  public ResponseEntity<UserAccount> findByUserName(@PathVariable("userName") String userName)
      throws EntityNotFoundException {
    UserAccount userAccount = userAccountService.findByUserName(userName);
    return ResponseEntity.created(URI.create(PATH + "/" + userAccount.getUserName()))
        .body(userAccount);
  }

  @PostMapping
  public ResponseEntity<UserAccount> add(@RequestBody UserAccount inputData) {
    UserAccount newUserAccount = userAccountService.add(inputData);

    return ResponseEntity.created(URI.create(PATH + "/" + newUserAccount.getUserName()))
        .body(newUserAccount);
  }

  @PutMapping("/{userName}")
  public ResponseEntity<UserAccount> update(
      @PathVariable("userName") String userName, @RequestParam("password") String password)
      throws EntityNotFoundException {

    return ResponseEntity.created(URI.create(PATH + "/" + userName))
        .body(userAccountService.update(userName, password));
  }

  @DeleteMapping("/{userName}")
  public ResponseEntity<Void> delete(@PathVariable("userName") String userName)
      throws EntityNotFoundException {
    userAccountService.deleteByUsername(userName);
    return ResponseEntity.noContent().build();
  }

  @PostMapping("/GetToken")
  public Token getAuthenticationToken(@RequestBody UserAccount userAccount)
      throws UnauthorizedAccessException, EntityNotFoundException {
    return jwtAuthenticationService.createToKen(userAccount);
  }
}
