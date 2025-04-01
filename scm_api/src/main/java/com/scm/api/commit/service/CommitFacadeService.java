package com.scm.api.commit.service;

import com.scm.api.commit.dto.CommitDetailOutput;
import com.scm.api.commit.dto.GithubCommitVO;
import com.scm.api.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.scm.api.commit.service
 * fileName       : CommitFacadeService
 * author         : leehyunjong
 * date           : 2025/03/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/13        leehyunjong       최초 생성
 */
@RequiredArgsConstructor
@Service
public class CommitFacadeService {

    private final CommitRestTemplateService commitRestTemplateService;

    public List<CommitDetailOutput> getCommitList(String owner, String repo, String branch) throws GlobalException {
        List<GithubCommitVO> output = commitRestTemplateService.getCommitList(owner, repo, branch);

        return output.stream().map(CommitDetailOutput::new).toList();
    }
}
