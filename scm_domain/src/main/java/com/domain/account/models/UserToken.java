package com.domain.account.models;

import io.micrometer.common.util.StringUtils;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;

/**
 * packageName    : com.domain.account.models
 * fileName       : UserToken
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "user_token")
public class UserToken {
    @EmbeddedId
    private AccountId accountId;

    @Column(name = "oauth_access_token")
    private String oauthAccessToken;
    @Column(name = "scm_access_token")
    private String scmAccessToken;

    @Builder
    public UserToken(AccountId accountId, String oAuthAccessToken, String scmAccessToken) {
        this.accountId = accountId;
        this.oauthAccessToken = oAuthAccessToken;
        this.scmAccessToken = scmAccessToken;
    }

    public void updateToken(String oauthAccessToken, String scmAccessToken) {
        if(!StringUtils.isEmpty(oauthAccessToken)) {
            this.oauthAccessToken = oauthAccessToken;
        }

        if(!StringUtils.isEmpty(scmAccessToken)) {
            this.scmAccessToken = scmAccessToken;
        }
    }
}
