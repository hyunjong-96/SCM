package com.scm.api.auth;

import com.scm.api.auth.service.AuthFacadeService;
import com.scm.api.auth.dto.LoginInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthFacadeService authFacadeService;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginInput input) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
