package com.scm.api.controller;

import com.domain.TestService;
import com.scm.api.dto.LoginInput;
import com.scm.api.dto.LoginOutput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class TestController {

    private final TestService testService;
    @GetMapping()
    public ResponseEntity<String> test() {
        String result = testService.test();
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutput> login(@RequestBody LoginInput input) {
        LoginOutput output = new LoginOutput();
        output.setId(input.getId());
        output.setPassword(input.getPassword());
        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
