package com.scm.api.repository;

import com.scm.api.exception.GlobalException;
import com.scm.api.repository.dto.UserRepoOutput;
import com.scm.api.repository.dto.branch.RepoBranchOutput;
import com.scm.api.repository.service.RepositoryFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName    : com.scm.api.repository
 * fileName       : RepositoryController
 * author         : leehyunjong
 * date           : 2025/03/11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/11        leehyunjong       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/repos")
@RestController
public class RepositoryController {

    private final RepositoryFacadeService repositoryFacadeService;

    @GetMapping("/users/{username}")
    public ResponseEntity<List<UserRepoOutput>> getUserRepos(@PathVariable("username") String username) throws GlobalException {
        List<UserRepoOutput> output = repositoryFacadeService.getUserRepos(username);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @GetMapping("/users/{username}/repo/{repo}/branches")
    public ResponseEntity<List<RepoBranchOutput>> getUserRepoBranch(
            @PathVariable(value = "username")String username, @PathVariable(value = "repo")String repo
    ) throws GlobalException {
        List<RepoBranchOutput> output = repositoryFacadeService.getUserRepoBranch(username, repo);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
