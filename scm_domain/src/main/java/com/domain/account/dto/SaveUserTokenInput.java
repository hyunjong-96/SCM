package com.domain.account.dto;

import com.domain.account.models.AccountId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.domain.account.dto
 * fileName       : SaveUserTokenInput
 * author         : leehyunjong
 * date           : 2025/03/06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/06        leehyunjong       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class SaveUserTokenInput {
    private AccountId accountId;
    private String oAuthAccessToken;
    private String scmAccessToken;

    @Builder
    public SaveUserTokenInput(AccountId accountId, String oAuthAccessToken, String scmAccessToken) {
        this.accountId = accountId;
        this.oAuthAccessToken = oAuthAccessToken;
        this.scmAccessToken = scmAccessToken;
    }
}
