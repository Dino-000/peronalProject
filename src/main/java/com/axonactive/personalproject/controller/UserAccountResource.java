package com.axonactive.personalproject.controller;

import com.axonactive.personalproject.exception.ResourceNotFoundException;
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
  public static final String PATH = "/api/UserAccounts";
  @Autowired UserAccountService userAccountService;
  @Autowired JwtAuthenticationService jwtAuthenticationService;


    @GetMapping
    public ResponseEntity<List<UserAccount>> getAll() {
        return ResponseEntity.ok().body(userAccountService.getAll());
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserAccount> getById (@PathVariable("userName") String userName) throws ResourceNotFoundException {
        UserAccount userAccount= userAccountService.findByUserName(userName).orElseThrow(()-> new ResourceNotFoundException("Can not found the account with this userName: "+userName));
           return ResponseEntity.created(URI.create(PATH+"/"+userAccount.getUserName())).body(userAccount);
    }

    @PostMapping
    public ResponseEntity<UserAccount> add(
            @RequestBody UserAccount inputData) {
        UserAccount newUserAccount = userAccountService.add(inputData);

        return ResponseEntity.created(URI.create(PATH + "/" + newUserAccount.getUserName())).body(newUserAccount);
    }

    @PutMapping("/{userName}")
    public  ResponseEntity<UserAccount> update(@PathVariable("id") String userName, @RequestParam("password") String inputData) throws ResourceNotFoundException {
        UserAccount updatingUserAccount = userAccountService.findByUserName(userName).orElseThrow(()->new ResourceNotFoundException("Can not found the account with this userName: "+userName));
        updatingUserAccount.setPassWord(inputData);

        UserAccount updatedUserAccount = userAccountService.add(updatingUserAccount);
        return  ResponseEntity.created(URI.create(PATH+"/"+updatedUserAccount.getUserName())).body(updatedUserAccount);
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> delete(@PathVariable("userName") String userName) throws ResourceNotFoundException {
        UserAccount deletingUserAccount = userAccountService.findByUserName(userName).orElseThrow(()->new ResourceNotFoundException("Can not found the account with this userName: "+userName));
        userAccountService.deleteByUsername(userName);
        return ResponseEntity.noContent().build();
    }

  @PostMapping("/GetToken")
  public Token getAuthenticationToken(@RequestBody UserAccount userAccount)
      throws UnauthorizedAccessException {
    return jwtAuthenticationService.createToKen(userAccount);
  }


}
