package com.scm.api.commit;

import com.scm.api.commit.dto.CommitDetailOutput;
import com.scm.api.commit.service.CommitFacadeService;
import com.scm.api.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * packageName    : com.scm.api.commits
 * fileName       : CommitController
 * author         : leehyunjong
 * date           : 2025/03/13
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/13        leehyunjong       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/commit")
@RestController
public class CommitController {

    private final CommitFacadeService commitFacadeService;

    @GetMapping("/username/{owner}/repo/{repo}/commit")
    public ResponseEntity<List<CommitDetailOutput>> getCommitList(
            @PathVariable("owner") String owner, @PathVariable("repo")String repo, @RequestParam(required = false, value = "branch") String branch) throws GlobalException {

        List<CommitDetailOutput> output = commitFacadeService.getCommitList(owner, repo, branch);

        return new ResponseEntity<>(output, HttpStatus.OK);
    }
}
