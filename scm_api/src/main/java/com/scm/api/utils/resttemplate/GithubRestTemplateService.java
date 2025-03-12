package com.scm.api.utils.resttemplate;

import com.scm.api.auth.model.PrincipalDetails;
import com.scm.api.exception.GlobalException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * packageName    : com.scm.api.utils.resttemplate
 * fileName       : GithubRestTemplate
 * author         : leehyunjong
 * date           : 2025/03/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/09        leehyunjong       최초 생성
 */
public class GithubRestTemplateService extends RestTemplateService{
    private final String defaultUri = "https://api.github.com";

    public GithubRestTemplateService(RestTemplateUtils restTemplateUtils) {
        super(restTemplateUtils);
    }

    public <T> ResponseEntity<List<T>> requestGetList(String uri, Class<T> responseType) throws GlobalException {
        HttpHeaders httpHeaders = getHttpHeaders();
        UriComponentsBuilder uriBuilder = this.buildGithubUri(uri);

        return super.requestGetList(httpHeaders, HttpMethod.GET, uriBuilder, responseType);
    }

    private UriComponentsBuilder buildGithubUri(String uri) {
        Map<String, Object> params = new HashMap<>();

        StringBuilder sb = new StringBuilder();
        sb.append(defaultUri);
        sb.append(uri);

        return buildUri(sb.toString(), params);

    }

    @Override
    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = super.getHttpHeaders();

        String accessToken = "Bearer "+getGithubAccessToken();

        httpHeaders.add("Authorization", accessToken);
        httpHeaders.add("Accept", "application/vnd.github+json");
        httpHeaders.add("Content-Type", "application/json;charset=utf-8");

        return httpHeaders;
    }

    private String getGithubAccessToken() {
        String providerAccessToken = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof PrincipalDetails) {
            PrincipalDetails principalDetails = (PrincipalDetails) authentication;
            providerAccessToken = principalDetails.getProviderAccessToken();
        }

        return providerAccessToken;
    }
}
