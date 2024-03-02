package mx.edu.utez.springbootmongodb.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import mx.edu.utez.springbootmongodb.models.user.User;

public class UserDetailsImpl implements UserDetails {

    private String mail;
    private String password;
    private boolean blocked;
    private boolean enabled;
    private String authorities;

    public UserDetailsImpl(String mail, String password, boolean blocked, boolean enabled, String authorities) {
        this.mail = mail;
        this.password = password;
        this.blocked = blocked;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getMail(), user.getPassword(), user.isBlocked(), user.isStatus(), user.getRole()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
