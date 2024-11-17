package com.scm.api.account.service;

import com.domain.account.dto.SaveAccountInput;
import com.domain.account.service.AccountService;
import com.scm.api.account.dto.SignInInput;
import com.scm.api.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.scm.api.account.service
 * fileName       : AccountService
 * author         : leehyunjong
 * date           : 2024/11/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/11/17        leehyunjong       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AccountFacadeService {

    private final AccountService accountService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signIn(SignInInput input) throws GlobalException {

        if(accountService.isExistAccount(input.getEmail())) {
            throw new GlobalException(input.getEmail() + "is exist");
        }

        SaveAccountInput accountInput = SaveAccountInput.builder()
                .email(input.getEmail())
                .password(bCryptPasswordEncoder.encode(input.getPassword()))
                .name(input.getName())
                .build();

        accountService.save(accountInput);
    }
}
