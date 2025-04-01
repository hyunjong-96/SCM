package com.scm.api.repository.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

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
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GithubRepoVO {
    private Long id;
    private String nodeId;
    private String name;
    private String fullName;
    private boolean isPrivate;
    private String htmlUrl;
    private String description;
    private String url;
    private Map<String, Object> owner;
}
