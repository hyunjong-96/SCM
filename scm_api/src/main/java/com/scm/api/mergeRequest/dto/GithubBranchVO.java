package com.scm.api.mergeRequest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * packageName    : com.scm.api.mergeRequest.dto
 * fileName       : GithubBranchVO
 * author         : leehyunjong
 * date           : 2025/03/09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/09        leehyunjong       최초 생성
 */
@ToString
@Getter
@AllArgsConstructor
public class GithubBranchVO {
    private String name;
    private GithubCommitVO commit;
    private boolean isProtected;

    @Getter
    @AllArgsConstructor
    public class GithubCommitVO {
        private String sha;
        private String url;
    }
}
