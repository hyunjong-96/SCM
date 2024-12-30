package com.domain.account.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * packageName     : com.domain.account.models
 * fileName       : LoginProvider
 * author         : leehyunjong
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        leehyunjong       최초 생성
 */
public enum LoginProvider {
    BASIC, GITLAB, GITHUB, KAKAO, GOOGLE;

    public static Map<String, LoginProvider> valuesMap = new HashMap<>();

    public String toValue() {return this.name().toLowerCase();}

    public String toString() {return this.name().toLowerCase();}

    static {
        LoginProvider[] arr = values();

        for(LoginProvider provider : arr) {
            valuesMap.put(provider.toValue(), provider);
        }
    }

    public static LoginProvider getLoginProvider(String value) {
        return valuesMap.get(value);
    }
}
