package com.domain.account.models;

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
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRolePk {
    private Long userId;
    private ScmRole role;

    @Builder
    public UserRolePk(Long userId, ScmRole role) {
        this.userId = userId;
        this.role = role;
    }
}
