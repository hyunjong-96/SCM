package com.scm.api.account.dto;

import com.domain.account.models.LoginProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.scm.api.account.dto
 * fileName       : signInInput
 * author         : leehyunjong
 * date           : 2024/11/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/11/17        leehyunjong       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class SignInInput {

    private String email;
    private String password;
    private String name;
}
