package com.domain.account.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

/**
 * packageName     : com.domain.account.models
 * fileName       : UserRolePk
 * author         : leehyunjong
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        leehyunjong       최초 생성
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoleId {
//    private AccountPk accountPk;
//    private ScmRole role;

//    @Id
//    @Column(name = "user_id")
    private AccountId accountId;

//    @Id
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private ScmRole role;

    @Builder
    public UserRoleId(AccountId accountId, ScmRole role) {
        this.accountId = accountId;
        this.role = role;
//        this.userId = userRoleId.getUserId();
//        this.role = userRoleId.getRole();
    }
}
