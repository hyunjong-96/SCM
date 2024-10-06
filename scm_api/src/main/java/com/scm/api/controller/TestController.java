package com.scm.api.controller;

import com.domain.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
