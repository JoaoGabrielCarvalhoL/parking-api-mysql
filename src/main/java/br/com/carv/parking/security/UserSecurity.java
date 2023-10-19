package br.com.carv.parking.security;

import br.com.carv.parking.entity.UserSystem;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class UserSecurity extends User {

    private UserSystem userSystem;

    public UserSecurity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserSecurity(UserSystem user) {
        super(user.getUsername(), user.getPasswordHash(),
                AuthorityUtils.createAuthorityList(user.getRole().name()));
        this.userSystem = user;
    }

    public UUID getId() { return userSystem.getId(); }

    public String getRole() {
        return userSystem.getRole().name();
    }
}
