package com.cornerfoodmarketwebsite.business.dto.request.domain;

import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AdministratorUserDetails implements UserDetails {

    private Administrator administrator;

    public AdministratorUserDetails(Administrator administrator) {
        this.administrator = administrator;
    }

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
        return !this.administrator.getIsDisabled();
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

    public Administrator getAdministrator() {
        return this.administrator;
    }
}
