package com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.domain;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private Customer customer;

    public CustomUserDetails(Customer customer) {
        this.customer = customer;
    }

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

    public String getFullName() {
        String fullName;

        System.out.println(this.customer.getMiddleName());
        if (this.customer.getMiddleName() != null && this.customer.getMiddleName() != "") {
            System.out.println("Hello");
            fullName = this.customer.getFirstName() + " " + this.customer.getMiddleName() + " " + this.customer.getLastName();
        } else {
            System.out.println("Hellaaaaa");
            fullName = this.customer.getFirstName() + " " + this.customer.getLastName();
        }
        return fullName;
    }
}
