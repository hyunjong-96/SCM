package com.domain.account.dto;

import com.domain.account.models.LoginProvider;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.domain.account.dto
 * fileName       : CreateOrUpdateTokenInput
 * author         : leehyunjong
 * date           : 2025/03/10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/10        leehyunjong       최초 생성
 */
@Getter
@AllArgsConstructor
public class TokenCreateOrUpdateInput {
    private Long id;
    private LoginProvider provider;
    private String oauthAccessToken;
    private String scmAccessToken;
}
