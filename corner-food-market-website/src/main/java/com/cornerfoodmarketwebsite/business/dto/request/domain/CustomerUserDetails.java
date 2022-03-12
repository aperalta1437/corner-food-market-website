package com.cornerfoodmarketwebsite.business.dto.request.domain;

import com.cornerfoodmarketwebsite.data.single_table.entity.Customer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@RequiredArgsConstructor
public class CustomerUserDetails implements UserDetails {

    private final Customer customer;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.customer.getPassword();
    }

    @Override
    public String getUsername() {
        return this.customer.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.customer.isDisabled();
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

        if (this.customer.getMiddleName() != null && this.customer.getMiddleName() != "") {
            fullName = this.customer.getFirstName() + " " + this.customer.getMiddleName() + " " + this.customer.getLastName();
        } else {
            fullName = this.customer.getFirstName() + " " + this.customer.getLastName();
        }
        return fullName;
    }

    public short getTotalCartItems() {
        return this.customer.getTotalCartItems();
    }
}
