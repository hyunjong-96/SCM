package com.domain.account.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

/**
 * packageName     : com.domain.account.models
 * fileName       : UserRole
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
//@IdClass(UserRolePk.class)
@Entity(name = "user_role")
public class UserRole implements Serializable {
//    @Id
//    @Column(name = "user_id")
//    private Long userId;
//
//    @Id
//    @Enumerated(value = EnumType.STRING)
//    @Column(name = "role")
//    private ScmRole role;

    @EmbeddedId
    private UserRoleId userRoleId;

    @Builder
    public UserRole(UserRoleId userRoleId) {
//        this.userId = userId;
//        this.role = role;
        this.userRoleId = userRoleId;
    }
}
