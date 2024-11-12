package com.scm.api.auth.service;

import com.domain.account.dto.SaveAccountInput;
import com.domain.account.service.AccountService;
import com.scm.api.dto.LoginInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AccountService accountService;

    public void login(LoginInput input) {
        SaveAccountInput accountInput = SaveAccountInput.builder()
                .email(input.getEmail())
                .password(input.getPassword())
                .build();

//        accountService.save(accountInput);
    }
}
