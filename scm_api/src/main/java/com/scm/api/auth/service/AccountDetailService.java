package com.scm.api.auth.service;

import com.domain.account.models.Account;
import com.domain.account.models.UserRole;
import com.domain.account.service.AccountService;
import com.scm.api.auth.model.AccountDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class AccountDetailService implements UserDetailsService {

    private final AccountService accountService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByEmail(username);
        if(account == null) {
            throw new UsernameNotFoundException(username + " is not exist!");
        }

        List<UserRole> userRoleList = accountService.findRoleByUserId(account.getId());

        return new AccountDetails(account, userRoleList);
    }
}
