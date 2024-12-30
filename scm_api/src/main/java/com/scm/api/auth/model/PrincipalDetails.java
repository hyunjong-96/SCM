package com.scm.api.auth.model;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

public class PrincipalDetails implements Authentication, OAuth2User {
    private AccountDetails account;
    private OAuth2Attribute attribute;

    private Long id;
    private String email;
    private String name;

    public PrincipalDetails(AccountDetails account) {
        this.account = account;

        this.id = account.getId();
        this.email = account.getEmail();
        this.name = account.getName();
    }

    public PrincipalDetails(OAuth2Attribute attributes) {
        this.attribute = attributes;

        this.id = attributes.getId();
        this.email = attributes.getEmail();
        this.name = attributes.getName();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attribute.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return String.valueOf(this.getId());
    }

    public Long getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUserName() {
        return this.name;
    }
}
