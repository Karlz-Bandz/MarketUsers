package com.izzisoft.users.repo;

import com.izzisoft.users.model.MarketUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MarketUserRepo extends JpaRepository<MarketUser, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<MarketUser> findByEmail(String email);
}
