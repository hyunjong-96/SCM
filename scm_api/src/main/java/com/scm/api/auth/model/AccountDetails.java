package com.scm.api.auth.model;

import com.domain.account.models.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AccountDetails extends Account implements UserDetails {

    public AccountDetails(Account account) {
        this.setId(account.getId());
        this.setEmail(account.getEmail());
        this.setName(account.getName());
        this.setPassword(account.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.getAccountPassword();
    }

    @Override
    public String getUsername() {
        return String.valueOf(this.getEmail());
    }
}
