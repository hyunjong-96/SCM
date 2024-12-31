package com.scm.api.auth.model;

import com.domain.account.models.ScmRole;
import com.domain.account.models.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

/**
 * packageName     : com.scm.api.auth.model
 * fileName       : OAuth2Attribute
 * author         : leehyunjong
 * date           : 2024-12-24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-24        leehyunjong       최초 생성
 */
@ToString
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class OAuth2Attribute implements OAuth2User {
    private Map<String, Object> attributes;
    private String attributeKey;
    private String email;
    private String name;
    private List<ScmRole> authorities = Collections.EMPTY_LIST;

    private String accessToken;

    private Long id;

    public static OAuth2Attribute of(String provider, String attributeKey,
                                     Map<String, Object> attributes) {
        switch (provider) {
            case "github":
                return ofGithub(attributeKey, attributes);
            default:
                throw new RuntimeException();
        }
    }

    private static OAuth2Attribute ofGithub(String attributeKey,
                                            Map<String, Object> attributes) {

        Integer id = (Integer) attributes.get("id");
        return OAuth2Attribute.builder()
                .id(Long.valueOf(id))
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .attributeKey(attributeKey)
                .authorities(Collections.singletonList(getRole((Boolean) attributes.get("site_admin"))))
                .build();
    }

    public void setAuthorities(List<UserRole> roleList) {
        this.authorities.addAll(roleList.stream().map(UserRole::getRole).toList());
    }

    private static ScmRole getRole(Boolean isAdmin) {
        return isAdmin(isAdmin) ? ScmRole.ROLE_ADMIN : ScmRole.ROLE_USER;
    }

    private static boolean isAdmin(Boolean isAdmin) {
        return isAdmin != null && isAdmin;
    }

    public Map<String, Object> convertToMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("key", attributeKey);
        map.put("name", name);
        map.put("email", email);
        map.put("id", id);
        return map;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream().map(ScmRole::name).map(role -> new SimpleGrantedAuthority(role.toUpperCase())).toList();
//        return Collections.singleton(new SimpleGrantedAuthority(this.authority));
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
