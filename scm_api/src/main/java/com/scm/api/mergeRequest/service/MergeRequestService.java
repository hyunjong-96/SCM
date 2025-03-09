package com.scm.api.mergeRequest.service;

import com.scm.api.exception.GlobalException;
import com.scm.api.mergeRequest.dto.GithubBranchVO;
import com.scm.api.utils.resttemplate.GithubRestTemplateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.scm.api.mergeRequest.service
 * fileName       : MergeRequestService
 * author         : leehyunjong
 * date           : 2025/03/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/09        leehyunjong       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class MergeRequestService {
    private final GithubRestTemplateService githubRestTemplateService;

    public void test() throws GlobalException {
        StringBuilder sb = new StringBuilder();
        sb.append("/repos/hyunjong-96/SCM/branches");
        ResponseEntity<List<GithubBranchVO>> result = githubRestTemplateService.requestGet(sb.toString(), GithubBranchVO.class);

        log.info(result.toString());
    }
}
