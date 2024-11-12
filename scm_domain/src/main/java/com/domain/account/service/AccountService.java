package com.domain.account.service;

import com.domain.account.dto.SaveAccountInput;
import com.domain.account.models.Account;
import com.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccountService{

    private final AccountRepository accountRepository;

    public void save(SaveAccountInput input) {
        Account newAccount = Account.builder()
                .email(input.getEmail())
                .password(input.getPassword())
                .name(input.getName())
                .build();

        accountRepository.save(newAccount);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
