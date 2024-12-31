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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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
@Component
public class OAuthLoginSuccessHandler extends LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final AccountService accountService;

    public OAuthLoginSuccessHandler(JwtAuthorizationProvider jwtAuthorizationProvider, AccountService accountService) {
        super(jwtAuthorizationProvider);
        this.accountService = accountService;
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

        String redirectUrl = super.getRedirectURL(response, authentication);

        response.sendRedirect(redirectUrl);
    }
}
