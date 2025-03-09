package com.scm.api.mergeRequest;

import com.scm.api.exception.GlobalException;
import com.scm.api.mergeRequest.service.MergeRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.scm.api.mergeRequest
 * fileName       : MergeRequestController
 * author         : leehyunjong
 * date           : 2025/03/02
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/02        leehyunjong       최초 생성
 */
@RequiredArgsConstructor
@RequestMapping("/api/merge-request")
@RestController
public class MergeRequestController {
    private final MergeRequestService mergeRequestService;

    @GetMapping
    public void test() throws GlobalException {
        mergeRequestService.test();
    }
}
