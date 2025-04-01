package com.scm.api.utils.resttemplate;

import com.scm.api.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.scm.api.utils.resttemplate
 * fileName       : RestTemplateService
 * author         : leehyunjong
 * date           : 2025/03/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/09        leehyunjong       최초 생성
 */
@RequiredArgsConstructor
public class RestTemplateService {

    public final RestTemplateUtils restTemplateUtils;

    protected  <T> ResponseEntity<List<T>> requestGetList(HttpHeaders httpHeaders, HttpMethod httpMethod, UriComponentsBuilder uri, Class<T> responseType) throws GlobalException {

        if(httpMethod.equals(HttpMethod.GET) ) {

            return restTemplateUtils.requestMultiGetMethod(httpHeaders, uri, responseType);
        }
        else {
            throw new GlobalException("Invalid HttpMethod");
        }
    }

    public HttpHeaders getHttpHeaders() {
        return restTemplateUtils.getDefaultHeaders();
    }

    public UriComponentsBuilder buildUri(String uri, Map<String, Object> params) {
        return restTemplateUtils.buildUri(uri, params);
    }
}
