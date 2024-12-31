package com.domain.account.service;

import com.domain.account.dto.SaveAccountInput;
import com.domain.account.models.*;
import com.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AccountService{

    private final AccountRepository accountRepository;
    private final UserRoleService userRoleService;

    public AccountService(AccountRepository accountRepository, UserRoleService userRoleService) {
        this.accountRepository = accountRepository;
        this.userRoleService = userRoleService;
    }

    public void save(SaveAccountInput input) {
        Account newAccount = Account.builder()
                .email(input.getEmail())
                .password(input.getPassword())
                .name(input.getName())
                .provider(input.getProvider())
                .build();

        accountRepository.save(newAccount);

        //UserRole 저장
        userRoleService.save(newAccount.getId(), ScmRole.ROLE_USER);
    }

    public boolean isExistAccount(String email) {
        return accountRepository.findByEmail(email) != null;
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public List<UserRole> findRoleByUserId(Long userId) {
        return userRoleService.findByUserId(userId);
    }
}
