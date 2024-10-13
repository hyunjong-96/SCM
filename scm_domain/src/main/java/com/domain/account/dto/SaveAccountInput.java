package com.domain.account.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SaveAccountInput {

    private String email;
    private String password;
    private String name;

    @Builder
    public SaveAccountInput(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
