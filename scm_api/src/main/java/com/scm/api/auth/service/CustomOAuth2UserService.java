package com.scm.api.auth.service;

import com.domain.account.dto.SaveAccountInput;
import com.domain.account.models.Account;
import com.domain.account.models.LoginProvider;
import com.domain.account.models.UserRole;
import com.domain.account.service.AccountService;
import com.scm.api.auth.model.OAuth2Attribute;
import com.scm.api.auth.model.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName     : com.scm.api.auth.service
 * fileName       : CustomOAuth2UserService
 * author         : leehyunjong
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        leehyunjong       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService {

    private final AccountService accountService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        log.info("registrationId : [{}]", registrationId);
        log.info("userNameAttributeName : [{}]", userNameAttributeName);
        log.info("oauth2User : [{}]", String.valueOf(oAuth2User));

        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        //존재하는 account의 oauth 로그인인 경우 권한 세팅
        if(accountService.isExistAccount(oAuth2User.getName())) {
//            Account account = accountService.findByEmail(oAuth2User.getName());
            Account account = accountService.findByIdAndProvider(oAuth2Attribute.getId(), LoginProvider.valuesMap.get(registrationId));
            List<UserRole> userRole = accountService.findRoleByUserId(account.getId());

            oAuth2Attribute.setAuthorities(userRole);
        }
        else {
            SaveAccountInput saveAccountInput = SaveAccountInput.builder()
                    .id(Long.valueOf((Integer)oAuth2User.getAttribute(oAuth2Attribute.getAttributeKey())))
                    .email(oAuth2User.getAttribute("email"))
                    .name(oAuth2User.getAttribute("name"))
                    .provider(LoginProvider.valuesMap.get(registrationId))
                    .build();

            accountService.save(saveAccountInput);
        }

        return new PrincipalDetails(oAuth2Attribute);
    }

}
