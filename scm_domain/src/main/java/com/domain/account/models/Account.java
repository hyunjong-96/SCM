package com.domain.account.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.ObjectUtils;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "provider")
    @Enumerated(value = EnumType.STRING)
    private LoginProvider provider;

    @Builder
    public Account(
            Long id,
            String email,
            String password,
            String name,
            LoginProvider provider
    ) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.provider = ObjectUtils.isEmpty(provider) ? LoginProvider.BASIC : provider;
    }

    public String getAccountPassword() {
        return this.password;
    }
}
