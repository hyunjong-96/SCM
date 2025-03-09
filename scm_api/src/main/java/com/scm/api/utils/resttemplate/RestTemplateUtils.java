package com.scm.api.utils.resttemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.scm.api.utils
 * fileName       : RestTemplateUtils
 * author         : leehyunjong
 * date           : 2025/03/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/09        leehyunjong       최초 생성
 */
@RequiredArgsConstructor
@Component
public class RestTemplateUtils {

    private final RestTemplate restTemplate;

    public HttpHeaders getDefaultHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return headers;
    }

    public void setHeaders(HttpHeaders httpHeaders, Map<String, String> params) {

        for(String key : params.keySet()) {
            httpHeaders.add(key, params.get(key));
        }

    }

    public UriComponentsBuilder buildUri(String uri, Map<String, Object> params) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(uri);

        for(String key : params.keySet()) {
            builder.queryParam(key, params.get(key));
        }

        return builder;
    }

    public <T> ResponseEntity<List<T>> requestGetMethod(HttpHeaders httpHeaders, UriComponentsBuilder uri, Class<T> requestType) {
        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<T>>() {}
        );

        return responseEntity;
    }

}
