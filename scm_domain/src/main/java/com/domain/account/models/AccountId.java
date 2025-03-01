package com.domain.account.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

/**
 * packageName    : com.domain.account.models
 * fileName       : AccountPk
 * author         : leehyunjong
 * date           : 2025/03/01
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/01        leehyunjong       최초 생성
 */
@Embeddable
@Getter
@NoArgsConstructor
public class AccountId {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "provider")
    @Enumerated(value = EnumType.STRING)
    private LoginProvider provider;

    @Builder
    public AccountId(Long id, LoginProvider provider) {
        this.id = id;
        this.provider = ObjectUtils.isEmpty(provider) ? LoginProvider.BASIC : provider;
    }
}
