package com.scm.api.repository.dto;

import com.scm.api.repository.dto.branch.GithubRepoBranchVO;
import com.scm.api.repository.dto.branch.RepoBranchOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Map;

/**
 * packageName    : com.scm.api.repository.dto
 * fileName       : RepoMapper
 * author         : leehyunjong
 * date           : 2025/03/18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025/03/18        leehyunjong       최초 생성
 */
@Mapper(componentModel = "spring")
public interface RepoMapper {
    /* Repository */
    @Mapping(target = "owner", source="owner", qualifiedByName = "mapOwnerToString")
    UserRepoOutput repoVoToRepoOutput(GithubRepoVO vo);

    List<UserRepoOutput> repoVoToRepoOutput(List<GithubRepoVO> vo);

    @Named("mapOwnerToString")
    default String mapOwnerToString(Map<String, Object> owner) {
        if(owner == null || !owner.containsKey("login")) {
            return null;
        }

        return owner.get("login").toString();
    }

    /* Branch */
    List<RepoBranchOutput> branchVoToBranchOutput(List<GithubRepoBranchVO> vo);
}
