package com.finalpretty.app.model;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.finalpretty.app.security.UserRole;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Integer id;

    @Column(name = "account", unique = true)
    private String account;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "register_date")
    private String registerDate;

    @Column(name = "change_password_date")
    private String changePasswordDate;

    @Enumerated(EnumType.STRING)
    private UserRole UserRole;
    private Boolean locked = false;
    private Boolean enabled = false;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_member_id", referencedColumnName = "member_id")
    private Member fkMember;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(UserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Users(String account, String email, String password, com.finalpretty.app.security.UserRole userRole) {
        this.account = account;
        this.email = email;
        this.password = password;
        this.UserRole = userRole;
        this.fkMember = new Member();

    }

}
