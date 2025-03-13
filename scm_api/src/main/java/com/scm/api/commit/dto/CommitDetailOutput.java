package com.scm.api.commit.dto;

import lombok.AllArgsConstructor;
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

    public CommitDetailOutput(GithubCommitVO vo) {
        this.sha = vo.getSha();
        this.nodeId = vo.getNodeId();
        this.commit = vo.getCommit();
        this.url = vo.getUrl();
        this.commentsUrl = vo.getCommentsUrl();
        this.parents = vo.getParents();
    }
}
