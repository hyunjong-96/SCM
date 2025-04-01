package com.scm.api.repository.service;

import com.scm.api.exception.GlobalException;
import com.scm.api.repository.dto.branch.GithubRepoBranchVO;
import com.scm.api.utils.resttemplate.GithubRestTemplateService;
import com.scm.api.utils.resttemplate.RestTemplateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * packageName    : com.scm.api.repository.service
 * fileName       : BranchRestTemplateService
 * author         : leehyunjong
 * date           : 2025/03/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/24        leehyunjong       최초 생성
 */
@Slf4j
@Component
public class BranchRestTemplateService extends GithubRestTemplateService {
    public BranchRestTemplateService(RestTemplateUtils restTemplateUtils) {
        super(restTemplateUtils);
    }

    public List<GithubRepoBranchVO> getUserRepoBranch(String username, String repo) throws GlobalException {
        final String uri = "/repos/"+username+"/"+repo+"/branches";

        HashMap<String, Object> params = new HashMap<>();

        ResponseEntity<List<GithubRepoBranchVO>> result = super.requestGithubGetList(uri, params, GithubRepoBranchVO.class);

        return result.getBody();
    }
}
