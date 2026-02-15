package com.izzisoft.users.service;

import com.izzisoft.users.exception.EmailNotExistsException;
import com.izzisoft.users.model.MarketUser;
import com.izzisoft.users.model.MarketUserRole;
import com.izzisoft.users.repo.MarketUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MarketUserDetailsService implements UserDetailsService {

    private final MarketUserRepo marketUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MarketUser foundUser = marketUserRepo.findByEmail(username).orElseThrow(
                () -> new EmailNotExistsException("Email not exists!")
        );

        return new User(foundUser.getEmail(), foundUser.getPassword(), mapRolesAuthority(foundUser.getRoles()));
    }

    private Collection<SimpleGrantedAuthority> mapRolesAuthority(Set<MarketUserRole> roles) {
        return roles.stream()
                .map(x -> new SimpleGrantedAuthority(x.name()))
                .toList();
    }
}
