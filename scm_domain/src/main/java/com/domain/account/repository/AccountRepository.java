package com.domain.account.repository;

import com.domain.account.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByEmail(String email);
}
