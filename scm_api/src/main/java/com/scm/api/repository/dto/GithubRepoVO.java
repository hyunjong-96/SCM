package com.scm.api.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.scm.api.repository.dto
 * fileName       : GithubRepoVO
 * author         : leehyunjong
 * date           : 2025/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/11        leehyunjong       최초 생성
 */
@Getter
@AllArgsConstructor
public class GithubRepoVO {
    private Long id;
    private String nodeId;
    private String name;
    private String fullName;
    private boolean isPrivate;
    private String htmlUrl;
    private String description;
    private String url;
}
