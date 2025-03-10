package com.scm.api.repository;

import com.scm.api.exception.GlobalException;
import com.scm.api.repository.dto.GithubRepoVO;
import com.scm.api.repository.service.RepositoryService;
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

    private final RepositoryService repositoryService;

    @GetMapping("/users/{username}")
    public ResponseEntity<List<GithubRepoVO>> getUserRepos(@PathVariable("username") String username) throws GlobalException {
        List<GithubRepoVO> output = repositoryService.getUserRepos(username);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
