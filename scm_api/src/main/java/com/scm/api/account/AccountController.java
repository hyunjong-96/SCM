package com.scm.api.account;

import com.scm.api.account.dto.SignInInput;
import com.scm.api.account.service.AccountFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.scm.api.account
 * fileName       : AccountController
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
@RestController
@RequestMapping("/api/account")
public class AccountController {

    private final AccountFacadeService accountFacadeService;

    @PostMapping("/save")
    public ResponseEntity<Void> signIn(@RequestBody SignInInput input) throws BadRequestException {
        accountFacadeService.signIn(input);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
