package com.scm.api.utils.resttemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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

    public <T> ResponseEntity<List<T>> requestMultiGetMethod(HttpHeaders httpHeaders, UriComponentsBuilder uri, Class<T> requestType) {
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        ParameterizedTypeReference typeReference = buildListResponseType(requestType, true);

        ResponseEntity<List<T>> responseEntity = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                entity,
                typeReference
        );

        return responseEntity;
    }

    public <T> ResponseEntity<T> requestSingleGetMethod(HttpHeaders httpHeaders, UriComponentsBuilder uri, Class<T> requestType) {
        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        ParameterizedTypeReference typeReference = buildListResponseType(requestType, false);

        ResponseEntity<T> responseEntity = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                entity,
                typeReference
        );

        return responseEntity;
    }

    private <T> ParameterizedTypeReference<?> buildListResponseType(Class<T> requestType, boolean isList) {
        return new ParameterizedTypeReference<>() {
            @Override
            public Type getType() {
                return getResponseType(requestType,isList);
            }
        };
    }

    private <T> ParameterizedType getResponseType(Class<T> requestType, boolean isList) {
        return new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{requestType};
            }

            @Override
            public Type getRawType() {
                return isList ? List.class : requestType;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };

    }

}
