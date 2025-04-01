package com.scm.api.repository.service;

import com.scm.api.exception.GlobalException;
import com.scm.api.repository.dto.GithubRepoVO;
import com.scm.api.repository.dto.RepoMapper;
import com.scm.api.repository.dto.UserRepoOutput;
import com.scm.api.repository.dto.branch.GithubRepoBranchVO;
import com.scm.api.repository.dto.branch.RepoBranchOutput;
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
public class RepositoryFacadeService {

    private final RepoMapper mapper;

    private final RepositoryRestTemplateService repositoryRestTemplateService;
    private final BranchRestTemplateService branchRestTemplateService;

    public List<UserRepoOutput> getUserRepos(String username) throws GlobalException {
        List<GithubRepoVO> repoVOList = repositoryRestTemplateService.getUsersRepositories(username);

        return mapper.repoVoToRepoOutput(repoVOList);
    }

    public List<RepoBranchOutput> getUserRepoBranch(String username, String repo) throws GlobalException {
        List<GithubRepoBranchVO> repoVoList = branchRestTemplateService.getUserRepoBranch(username, repo);

        return mapper.branchVoToBranchOutput(repoVoList);
    }
}
