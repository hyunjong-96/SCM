package com.scm.api.repository.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.scm.api.repository.dto
 * fileName       : UserRepoOutput
 * author         : leehyunjong
 * date           : 2025/03/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/18        leehyunjong       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
public class UserRepoOutput {
    private Long id;
    private String nodeId;
    private String name;
    private String fullName;
    private boolean isPrivate;
    private String htmlUrl;
    private String description;
    private String url;
    private String owner;
}
