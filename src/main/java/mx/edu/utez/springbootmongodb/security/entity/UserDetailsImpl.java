package mx.edu.utez.springbootmongodb.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import mx.edu.utez.springbootmongodb.models.user.User;

public class UserDetailsImpl implements UserDetails {

    private String mail;
    private String password;
    private boolean blocked;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String mail, String password, boolean blocked, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        this.mail = mail;
        this.password = password;
        this.blocked = blocked;
        this.enabled = enabled;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        Set<GrantedAuthority> authorities = user.getRole().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return new UserDetailsImpl(
                user.getMail(), user.getPassword(), user.isBlocked(), user.isStatus(), authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !blocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
