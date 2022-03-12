package com.cornerfoodmarketwebsite.business.dto.request.domain;

import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class AdministratorUserDetails implements UserDetails {

    private final Administrator administrator;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.administrator.getPassword();
    }

    @Override
    public String getUsername() {
        return this.administrator.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.administrator.isDisabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        String fullName;

        if (this.administrator.getMiddleName() != null && this.administrator.getMiddleName() != "") {
            fullName = this.administrator.getFirstName() + " " + this.administrator.getMiddleName() + " " + this.administrator.getLastName();
        } else {
            fullName = this.administrator.getFirstName() + " " + this.administrator.getLastName();
        }
        return fullName;
    }
}
