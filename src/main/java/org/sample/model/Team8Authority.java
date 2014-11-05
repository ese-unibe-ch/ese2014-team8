package org.sample.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by zilti on 05.11.14.
 */
public class Team8Authority implements GrantedAuthority {

    private String authority;

    public Team8Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
