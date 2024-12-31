package com.domain.account.service;

import com.domain.account.models.ScmRole;
import com.domain.account.models.UserRole;
import com.domain.account.models.UserRolePk;
import com.domain.account.repository.UserRoleRepository;
import com.exception.GlobalDomainException;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * packageName     : com.domain.account.service
 * fileName       : UserRoleService
 * author         : leehyunjong
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        leehyunjong       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@Service
class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public void save(Long userId, ScmRole role) {
        UserRolePk pk = UserRolePk.builder()
                .userId(userId)
                .role(role)
                .build();

        UserRole userRole = userRoleRepository.findById(pk).orElse(null);

        if(userRole != null) {
            throw new GlobalDomainException("exist user_role");
        }

        UserRole newUserRole = UserRole.builder()
                .userId(userId)
                .role(role)
                .build();

        userRoleRepository.save(newUserRole);
    }

    public List<UserRole> findByUserId(Long userId) {
        return userRoleRepository.findByUserId(userId);
    }
}
