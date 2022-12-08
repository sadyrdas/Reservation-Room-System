package cz.cvut.kbss.ear.mroom.security.model;

import cz.cvut.kbss.ear.mroom.model.User;
import cz.cvut.kbss.ear.mroom.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

public class UserDetails implements org.springframework.security.core.userdetails.UserDetails{

    private User user;

    private final Set<GrantedAuthority> authorities;

    public UserDetails(User user){
        this.user = Objects.requireNonNull(user, "Cannot create User Details: User is null!");
        this.authorities = new HashSet<>();
        addUserRole();
    }

    public UserDetails(User user, Collection<GrantedAuthority> authorities) {
        this.user = Objects.requireNonNull(user, "Cannot create User Details: User is null!");
        this.authorities = new HashSet<>();
        addUserRole();
        this.authorities.addAll(Objects.requireNonNull(authorities, "Cannot create User Details: authorities are null!"));
    }

    private void addUserRole() {
        List<UserRole> roles = user.getRoles();
        authorities.addAll(roles.stream().map(i -> new SimpleGrantedAuthority(i.getName())).collect(Collectors.toSet()));
    }

    public User getUser(){
        return user;
    }

    public void eraseCredentials(){
        user.erasePassword();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.unmodifiableCollection(authorities);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
