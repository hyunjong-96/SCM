package com.domain.account.repository;

import com.domain.account.models.UserRole;
import com.domain.account.models.UserRolePk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * packageName     : com.domain.account.repository
 * fileName       : UserRoleRepository
 * author         : leehyunjong
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        leehyunjong       최초 생성
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRolePk> {
    List<UserRole> findByUserId(Long userId);
}
