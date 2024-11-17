package com.scm.api.auth.service;

import com.domain.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthFacadeService {

    private final AccountService accountService;

}
