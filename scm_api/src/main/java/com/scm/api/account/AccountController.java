package com.scm.api.account;

import com.scm.api.account.dto.SignInInput;
import com.scm.api.account.service.AccountFacadeService;
import com.scm.api.auth.model.PrincipalDetails;
import com.scm.api.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> signIn(@RequestBody SignInInput input) throws GlobalException {
        accountFacadeService.signIn(input);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/test")
    public ResponseEntity<String> test(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return new ResponseEntity<>(principalDetails.getUserName(),HttpStatus.OK);
    }
}
