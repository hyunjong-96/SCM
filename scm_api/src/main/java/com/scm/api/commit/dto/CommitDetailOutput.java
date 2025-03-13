package com.scm.api.commit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * packageName    : com.scm.api.commit.dto
 * fileName       : CommitVO
 * author         : leehyunjong
 * date           : 2025/03/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/13        leehyunjong       최초 생성
 */
@Getter
@NoArgsConstructor
public class CommitDetailOutput {
    private String sha;
    private String nodeId;
    private GithubCommitInfoVO commit;
    private String url;
    private String commentsUrl;
    private List<GithubCommitSimpleVO> parents;

    @Builder
    public CommitDetailOutput(String sha, String nodeId, GithubCommitInfoVO commit, String url, String commentsUrl, List<GithubCommitSimpleVO> parents) {
        this.sha = sha;
        this.nodeId = nodeId;
        this.commit = commit;
        this.url = url;
        this.commentsUrl = commentsUrl;
        this.parents = parents;
    }
}
