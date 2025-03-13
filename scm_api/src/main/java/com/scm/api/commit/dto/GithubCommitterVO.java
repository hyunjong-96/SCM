package com.scm.api.commit.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * packageName    : com.scm.api.commit.dto
 * fileName       : GithubCommitterVO
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
public class GithubCommitterVO {
    private String name;
    private String email;
    private LocalDateTime date;
}
