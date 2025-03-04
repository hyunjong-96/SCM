package com.scm.api.auth;

import com.scm.api.auth.model.PrincipalDetails;
import com.scm.api.auth.service.AuthFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthFacadeService authFacadeService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/oauth/callback")
//    public OAuth2User callback(@AuthenticationPrincipal OAuth2User user) {
//        return user;
//    }
}
