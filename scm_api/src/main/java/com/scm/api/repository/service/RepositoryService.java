package com.scm.api.repository.service;

import com.scm.api.exception.GlobalException;
import com.scm.api.repository.dto.GithubRepoVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName    : com.scm.api.repository.service
 * fileName       : RepositoryService
 * author         : leehyunjong
 * date           : 2025/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/11        leehyunjong       최초 생성
 */
@RequiredArgsConstructor
@Service
public class RepositoryService {

    private final RepositoryRestTemplateService repositoryRestTemplateService;

    public List<GithubRepoVO> getUserRepos(String username) throws GlobalException {
        return repositoryRestTemplateService.getUsersRepositories(username);
    }
}
