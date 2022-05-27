package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AdministratorUserDetails;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("AdministratorUserDetailsService")
public class AdministratorUserDetailsService implements UserDetailsService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Administrator> optionalAdministrator = administratorRepository.findByEmail(email);
        if (optionalAdministrator.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new AdministratorUserDetails(optionalAdministrator.get());
    }
}
