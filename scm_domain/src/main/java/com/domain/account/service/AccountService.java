package com.domain.account.service;

import com.domain.account.dto.SaveAccountInput;
import com.domain.account.models.Account;
import com.domain.account.models.LoginProvider;
import com.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class AccountService{

    private final AccountRepository accountRepository;

    public void save(SaveAccountInput input) {
        Account newAccount = Account.builder()
                .email(input.getEmail())
                .password(input.getPassword())
                .name(input.getName())
                .provider(input.getProvider())
                .build();

        accountRepository.save(newAccount);
    }

    public boolean isExistAccount(String email) {
        return accountRepository.findByEmail(email) != null;
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
