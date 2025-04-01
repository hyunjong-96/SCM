package com.scm.api.commit.service;

import com.scm.api.commit.dto.GithubCommitVO;
import com.scm.api.exception.GlobalException;
import com.scm.api.utils.resttemplate.GithubRestTemplateService;
import com.scm.api.utils.resttemplate.RestTemplateUtils;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * packageName    : com.scm.api.commit.service
 * fileName       : CommitRestTemplateService
 * author         : leehyunjong
 * date           : 2025/03/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/13        leehyunjong       최초 생성
 */
@Component
public class CommitRestTemplateService extends GithubRestTemplateService {
    public CommitRestTemplateService(RestTemplateUtils restTemplateUtils) {
        super(restTemplateUtils);
    }

    public List<GithubCommitVO> getCommitList(String owner, String repo, String branch) throws GlobalException {
        StringBuilder sb = new StringBuilder();
        sb.append("/repos/").append(owner).append("/").append(repo).append("/").append("commits");

        HashMap<String, Object> params = new HashMap<>();

        if(StringUtils.isNotEmpty(branch)) {
            params.put("sha", branch);
        }

        return requestGithubGetList(sb.toString(), params, GithubCommitVO.class).getBody();
    }
}
