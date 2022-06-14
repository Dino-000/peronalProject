package com.axonactive.personalproject.entity.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Permission extends  BaseEntity{
    private String PermissionName;
    private  String PermissionKey;
}
