package com.domain;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class TestService {

    public String test() {
        return "test";
    }
}
