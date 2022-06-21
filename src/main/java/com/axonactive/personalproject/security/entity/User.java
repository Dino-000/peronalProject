package com.axonactive.personalproject.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(unique = true, nullable = false)
  private String userName;

  @JsonIgnore private String password;

  private boolean active;

  @OneToMany(mappedBy = "user")
  private List<UserRoleAssignment> roles = new ArrayList<>();
}
