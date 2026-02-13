package com.izzisoft.users.dto;

import com.izzisoft.users.model.MarketUserRole;

import java.util.Set;

public record MarketUserResponse(
        Long id,
        String username,
        String email,
        Set<MarketUserRole> roles
) {
}
