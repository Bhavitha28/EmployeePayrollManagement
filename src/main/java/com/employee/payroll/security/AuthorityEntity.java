package com.employee.payroll.security;

import com.employee.payroll.entities.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AuthorityEntity implements GrantedAuthority{
    private String authority;
    public AuthorityEntity(String authority){
        this.authority=authority;
    }

    @Override
    public String getAuthority(){
        return this.authority;
    }


}
