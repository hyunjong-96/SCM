package com.domain.account.repository;

import com.domain.account.models.Account;
import com.domain.account.models.AccountId;
import com.domain.account.models.LoginProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, AccountId> {
    public Account findByEmail(String email);
//    Account findByIdAndProvider(Long id, LoginProvider provider);

    Long findAccountId_IdByAccountId_Provider(LoginProvider loginProvider);
}
