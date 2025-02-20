package com.scm.api.auth.handler;

import com.domain.account.dto.SaveAccountInput;
import com.domain.account.models.Account;
import com.domain.account.models.LoginProvider;
import com.domain.account.service.AccountService;
import com.scm.api.auth.model.PrincipalDetails;
import com.scm.api.auth.provider.JwtAuthorizationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

/**
 * packageName     : com.scm.api.auth.handler
 * fileName       : LoginOAuthSuccessHandler
 * author         : leehyunjong
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        leehyunjong       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OAuthLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${login.success.url}")
    protected String redirectURL;

    private final AccountService accountService;
    private final JwtAuthorizationProvider jwtAuthorizationProvider;

//    public OAuthLoginSuccessHandler(JwtAuthorizationProvider jwtAuthorizationProvider, AccountService accountService) {
////        super(jwtAuthorizationProvider);
//        this.accountService = accountService;
//    }

    public String getRedirectURL(HttpServletResponse response, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        log.info( "Success login. JWT 발급. username: {}" ,principalDetails.getEmail());

        //todo : 로그인 성공 후 JWT 발급 후 성공 화면으로 redirect할 부분.
        String accessToken = jwtAuthorizationProvider.generateToken(String.valueOf(principalDetails.getEmail()));

        String redirectUrl = UriComponentsBuilder.fromUriString(redirectURL)
                .queryParam("scm-token", accessToken)
                .build()
                .toString();

        return redirectUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

//        auth2AuthenticationToken.
        if(!accountService.isExistAccount(auth2AuthenticationToken.getName())) {
            PrincipalDetails principalDetails = (PrincipalDetails)auth2AuthenticationToken.getPrincipal();

            SaveAccountInput saveAccountInput = SaveAccountInput.builder()
                    .email(auth2AuthenticationToken.getName())
                    .provider(LoginProvider.getLoginProvider(auth2AuthenticationToken.getAuthorizedClientRegistrationId()))
                    .name(principalDetails.getUserName())
                    .build();

            accountService.save(saveAccountInput);
        }

        String redirectUrl = this.getRedirectURL(response, authentication);

        response.sendRedirect(redirectUrl);
    }
}
