package com.domain.account.service;

import com.domain.account.dto.SaveAccountInput;
import com.domain.account.models.*;
import com.domain.account.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

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

        Long id = input.getId();
        if(ObjectUtils.isEmpty(input.getProvider()) || input.getProvider().equals(LoginProvider.BASIC)) {
            id = this.getBasicProviderMaxId();
        }

        AccountId newAccountId = AccountId.builder()
                .id(id)
                .provider(input.getProvider())
                .build();

        Account newAccount = Account.builder()
                .accountId(newAccountId)
                .email(input.getEmail())
                .password(input.getPassword())
                .name(input.getName())
                .build();

        accountRepository.save(newAccount);

        //UserRole 저장
        if(userRoleService.findByUserId(newAccountId).isEmpty()) {
            userRoleService.save(newAccount.getAccountId(), ScmRole.ROLE_USER);
        }
    }

    public boolean isExistAccount(String email) {
        return accountRepository.findByEmail(email) != null;
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public List<UserRole> findRoleByUserId(AccountId accountId) {
        return userRoleService.findByUserId(accountId);
    }

    public Account findByPk(AccountId accountId) {
//        return accountRepository.findByIdAndProvider(id, provider);
        return accountRepository.findById(accountId).orElse(null);
    }

    public Long getProviderMaxId(LoginProvider provider) {
        return accountRepository.findAccountId_IdByAccountId_Provider(provider);
    }

    public Long getBasicProviderMaxId() {
        return getProviderMaxId(LoginProvider.BASIC);
    }
}
