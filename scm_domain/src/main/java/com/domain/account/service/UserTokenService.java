package com.domain.account.service;

import com.domain.account.dto.SaveUserTokenInput;
import com.domain.account.models.UserToken;
import com.domain.account.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.domain.account.service
 * fileName       : UserTokenService
 * author         : leehyunjong
 * date           : 2025/03/06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/06        leehyunjong       최초 생성
 */
@RequiredArgsConstructor
@Service
public class UserTokenService {

    private final UserTokenRepository userTokenRepository;

    public void save(SaveUserTokenInput saveUserTokenInput) {
        UserToken newUserToken = UserToken.builder()
                .accountId(saveUserTokenInput.getAccountId())
                .oAuthAccessToken(saveUserTokenInput.getOAuthAccessToken())
                .scmAccessToken(saveUserTokenInput.getScmAccessToken())
                .build();

        userTokenRepository.save(newUserToken);
    }

    public UserToken findByOauthAccessToken(String oauthAccessToken) {
        return userTokenRepository.findByOauthAccessToken(oauthAccessToken);
    }
}
