package com.scm.api.auth.provider;

import com.domain.account.models.Account;
import com.scm.api.auth.exception.LoginAuthException;
import com.scm.api.auth.model.AccountDetails;
import com.scm.api.auth.model.PrincipalDetails;
import com.scm.api.auth.service.AccountDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class AuthCustomProvider implements AuthenticationProvider {

    private final AccountDetailService accountDetailService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthCustomProvider(AccountDetailService accountDetailService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountDetailService = accountDetailService;
        this.passwordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info(authentication.getName());

        UserDetails principalDetail = accountDetailService.loadUserByUsername(authentication.getName());

        try{
            this.checkPassword(principalDetail, authentication);
        }
        catch (LoginAuthException ex) {
            throw new LoginAuthException(ex);
        }

        Authentication principal = new PrincipalDetails((AccountDetails) principalDetail);

        return principal;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.
                isAssignableFrom(authentication);
    }

    private void checkPassword(UserDetails userDetails, Authentication authentication) throws LoginAuthException {
        if(authentication.getCredentials() == null) {
            log.info("password is null");
            throw new BadCredentialsException("INVALID Password !");
        }

        String inputPassword = authentication.getCredentials().toString();

        if(!this.passwordEncoder.matches(inputPassword, userDetails.getPassword())) {
            log.info("fail match password");
            throw new LoginAuthException("INVALID Password");
        }
    }
}
