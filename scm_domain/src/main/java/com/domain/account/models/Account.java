package com.domain.account.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "account")
public class Account {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column(name = "provider")
//    @Enumerated(value = EnumType.STRING)
//    private LoginProvider provider;
    @EmbeddedId
    private AccountId accountId;

    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;

    @Builder
    public Account(
//            Long id,
//            LoginProvider provider
            AccountId accountId,
            String email,
            String password,
            String name
    ) {
//        this.id = id;
//        this.provider = ObjectUtils.isEmpty(provider) ? LoginProvider.BASIC : provider;
        this.accountId = accountId;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public String getAccountPassword() {
        return this.password;
    }
}
