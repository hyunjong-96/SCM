package com.scm.api.commit.service;

import com.scm.api.commit.dto.CommitDetailOutput;
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

    public List<CommitDetailOutput> getCommitList(String owner, String repo, String branch) {

    }
}
