package com.scm.api.auth.provider;

import com.domain.account.models.LoginProvider;
import com.scm.api.auth.model.AccountDetails;
import com.scm.api.auth.model.PrincipalDetails;
import com.scm.api.auth.service.AccountDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.scm.api.auth.provider
 * fileName       : JwtAuthorizationProvider
 * author         : leehyunjong
 * date           : 2024/11/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/11/24        leehyunjong       최초 생성
 */
@Slf4j
@Component
public class JwtAuthorizationProvider {

    private final long EXPIRED_TIME = 1000L*60*60;
    private final AccountDetailService accountDetailService;

    public JwtAuthorizationProvider(AccountDetailService accountDetailService) {
        this.accountDetailService = accountDetailService;
    }

    @Value("${jwt.secret}")
    private String secretKey;


    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build().parseClaimsJws(token);

        return claims.getBody().getExpiration().after(new Date());
    }

    public Authentication authentication(String token) {
        String username = this.getUsername(token);

        AccountDetails accountDetails = (AccountDetails) accountDetailService.loadUserByUsername(username);

        return new PrincipalDetails(accountDetails);
//        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword());
    }

    private String getUsername(String jwt) {
        return Jwts.parserBuilder().setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody()
                .getSubject();
    }

    private Key getSecretKey() {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String id, LoginProvider provider) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("provider",provider);
        claims.put("id",id);

        return Jwts.builder()
//                .setSubject(username)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + EXPIRED_TIME))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
