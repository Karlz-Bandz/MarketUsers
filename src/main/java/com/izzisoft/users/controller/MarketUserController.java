package com.izzisoft.users.controller;

import com.izzisoft.users.dto.MarketUserLoginRequest;
import com.izzisoft.users.dto.MarketUserRegisterRequest;
import com.izzisoft.users.dto.MarketUserResponse;
import com.izzisoft.users.dto.TokenResponse;
import com.izzisoft.users.service.MarketUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MarketUserController {

    private final MarketUserService marketUserService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> loginUser(@RequestBody MarketUserLoginRequest marketUserLoginRequest) {
        TokenResponse tokenResponse = new TokenResponse(this.marketUserService.loginUser(marketUserLoginRequest));
        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<MarketUserResponse> registerUser(@Valid @RequestBody MarketUserRegisterRequest marketUserRegisterRequest) {
        return new ResponseEntity<>(this.marketUserService.registerUser(marketUserRegisterRequest), HttpStatus.CREATED);
    }
}
