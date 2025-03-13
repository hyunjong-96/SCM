package com.scm.api.commit.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubCommitVO {
    private String sha;
    private String nodeId;
    private GithubCommitInfoVO commit;
    private String url;
    private String commentsUrl;
    private List<GithubCommitSimpleVO> parents;
}
