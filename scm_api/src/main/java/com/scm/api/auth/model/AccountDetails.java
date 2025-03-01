package com.scm.api.auth.model;

import com.domain.account.models.Account;
import com.domain.account.models.ScmRole;
import com.domain.account.models.UserRole;
import com.domain.account.models.UserRoleId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class AccountDetails extends Account implements UserDetails {

    private List<String> authorities;

    public AccountDetails(Account account, List<UserRole> authorities) {
        this.setAccountId(account.getAccountId());
        this.setEmail(account.getEmail());
        this.setName(account.getName());
        this.setPassword(account.getPassword());

        this.authorities = authorities.stream().map(UserRole::getUserRoleId).map(UserRoleId::getRole).map(ScmRole::name).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream().map(role -> new SimpleGrantedAuthority(role.toUpperCase())).toList();
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
