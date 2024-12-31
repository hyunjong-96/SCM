package com.domain.account.dto;

import com.domain.account.models.LoginProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Getter
@Setter
@NoArgsConstructor
public class SaveAccountInput {

    private String email;
    private String password;
    private String name;
    private LoginProvider provider;

    @Builder
    public SaveAccountInput(String email, String password, String name, LoginProvider provider) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.provider = provider;
    }
}
