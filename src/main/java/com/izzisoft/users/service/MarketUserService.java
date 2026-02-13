package com.izzisoft.users.service;

import com.izzisoft.users.dto.MarketUserRegisterRequest;
import com.izzisoft.users.dto.MarketUserResponse;
import com.izzisoft.users.exception.EmailAlreadyExistsException;
import com.izzisoft.users.exception.UsernameAlreadyExistsException;
import com.izzisoft.users.model.MarketUser;
import com.izzisoft.users.repo.MarketUserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MarketUserService {

    private final MarketUserRepo marketUserRepo;

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
                .password(marketUserRegisterRequest.password())
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
