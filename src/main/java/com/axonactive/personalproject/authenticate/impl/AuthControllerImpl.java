package com.axonactive.personalproject.authenticate.impl;

import com.axonactive.personalproject.authenticate.AuthController;
import com.axonactive.personalproject.security.jwt.JwtRequest;
import com.axonactive.personalproject.security.jwt.JwtResponse;
import com.axonactive.personalproject.security.jwt.JwtUtils;
import com.axonactive.personalproject.security.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {
  private final AuthenticationManager authenticateManager;
  private final JwtUtils jwtUtils;

  @Override
  public ResponseEntity<?> authenticateUser(JwtRequest loginRequest) {
    Authentication authentication =
        authenticateManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String jwt = jwtUtils.generateJwtToken(authentication);

      UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
      List<String> roles= userDetails.getAuthorities().stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt,userDetails.getUsername(),roles));
  }
}
