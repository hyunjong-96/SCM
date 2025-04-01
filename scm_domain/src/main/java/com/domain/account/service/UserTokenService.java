package com.domain.account.service;

import com.domain.account.dto.TokenCreateOrUpdateInput;
import com.domain.account.dto.SaveUserTokenInput;
import com.domain.account.models.AccountId;
import com.domain.account.models.UserToken;
import com.domain.account.repository.UserTokenRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional
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

    public UserToken findUsersToken(AccountId accountId) {
        return userTokenRepository.findById(accountId).orElse(null);
    }

    public void createOrUpdateToken(TokenCreateOrUpdateInput input) {
//        UserToken userToken = userTokenService.findUsersToken(principalDetails.getAccount().getAccountId());
        AccountId accountId = new AccountId(input.getId(), input.getProvider());
        UserToken userToken = userTokenRepository.findById(accountId).orElse(null);

        if(userToken == null) {
            SaveUserTokenInput saveUserTokenInput = SaveUserTokenInput.builder()
                    .accountId(accountId)
                    .oAuthAccessToken(input.getOauthAccessToken())
                    .scmAccessToken(input.getScmAccessToken())
                    .build();

            this.save(saveUserTokenInput);
        }
        else {

            if(!StringUtils.isEmpty(input.getOauthAccessToken())) {
                userToken.setOauthAccessToken(input.getOauthAccessToken());
            }

            if(!StringUtils.isEmpty(input.getScmAccessToken())) {
                userToken.setScmAccessToken(input.getScmAccessToken());
            }
        }
    }

}
