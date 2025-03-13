package com.scm.api.repository.service;

import com.scm.api.exception.GlobalException;
import com.scm.api.repository.dto.GithubRepoVO;
import com.scm.api.utils.resttemplate.GithubRestTemplateService;
import com.scm.api.utils.resttemplate.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * packageName    : com.scm.api.repository.service
 * fileName       : RepositoryRestTemplateService
 * author         : leehyunjong
 * date           : 2025/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/11        leehyunjong       최초 생성
 */
@Slf4j
@Component
public class RepositoryRestTemplateService extends GithubRestTemplateService {
    public RepositoryRestTemplateService(RestTemplateUtils restTemplateUtils) {
        super(restTemplateUtils);
    }

    public List<GithubRepoVO> getUsersRepositories(String username) throws GlobalException {
        final String uri = "/users/"+username+"/repos";

        HashMap<String, Object> params = new HashMap<>();

        ResponseEntity<List<GithubRepoVO>> result = super.requestGithubGetList(uri, params, GithubRepoVO.class);

        return result.getBody();
    }
}
