package com.scm.api.auth.service;

import com.scm.api.auth.model.OAuth2Attribute;
import com.scm.api.auth.model.PrincipalDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

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
@Service
public class CustomOAuth2UserService implements OAuth2UserService {
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


        return new PrincipalDetails(oAuth2Attribute);
    }

}
