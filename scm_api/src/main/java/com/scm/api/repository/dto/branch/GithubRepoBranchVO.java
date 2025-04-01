package com.scm.api.repository.dto.branch;

import com.scm.api.commit.dto.GithubCommitSimpleVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.scm.api.repository.dto
 * fileName       : GithubRepoBranchVO
 * author         : leehyunjong
 * date           : 2025/03/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/24        leehyunjong       최초 생성
 */
@Getter
@NoArgsConstructor
public class GithubRepoBranchVO {
    private String name;
    private GithubCommitSimpleVO commit;
    private boolean isProtected;
}
