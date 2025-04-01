package com.domain.account.repository;

import com.domain.account.models.AccountId;
import com.domain.account.models.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.domain.account.repository
 * fileName       : UserTokenRepository
 * author         : leehyunjong
 * date           : 2025/03/06
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/06        leehyunjong       최초 생성
 */
@Repository
public interface UserTokenRepository extends JpaRepository<UserToken, AccountId> {

    UserToken findByOauthAccessToken(String oauthAccessToken);
}
