package com.izzisoft.users.service;

import com.izzisoft.users.dto.MarketUserLoginRequest;
import com.izzisoft.users.dto.MarketUserRegisterRequest;
import com.izzisoft.users.dto.MarketUserResponse;
import com.izzisoft.users.exception.EmailAlreadyExistsException;
import com.izzisoft.users.exception.UsernameAlreadyExistsException;
import com.izzisoft.users.model.MarketUser;
import com.izzisoft.users.repo.MarketUserRepo;
import com.izzisoft.users.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketUserService {

    private final MarketUserRepo marketUserRepo;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final TokenService tokenService;

    public String loginUser(MarketUserLoginRequest marketUserLoginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(marketUserLoginRequest.email(), marketUserLoginRequest.password())
        );

        if (authentication.isAuthenticated()) {
            return tokenService.generateToken(authentication);
        }

        return "Wrong password!";
    }

    public MarketUserResponse registerUser(MarketUserRegisterRequest marketUserRegisterRequest) {

        if (marketUserRepo.existsByEmail(marketUserRegisterRequest.email())) {
            throw new EmailAlreadyExistsException("Provided email already exists!");
        }

        if (marketUserRepo.existsByUsername(marketUserRegisterRequest.username())) {
            throw new UsernameAlreadyExistsException("Provided username already exits!");
        }

        MarketUser marketUser = MarketUser.builder()
                .username(marketUserRegisterRequest.username())
                .email(marketUserRegisterRequest.email())
                .password(passwordEncoder.encode(marketUserRegisterRequest.password()))
                .build();

        MarketUser createdUser = marketUserRepo.save(marketUser);

        return new MarketUserResponse(
                createdUser.getId(),
                createdUser.getUsername(),
                createdUser.getEmail(),
                createdUser.getRoles()
        );
    }
}
