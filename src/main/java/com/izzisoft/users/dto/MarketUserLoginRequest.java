package com.izzisoft.users.dto;

public record MarketUserLoginRequest(
        String email,
        String password
) {
}
