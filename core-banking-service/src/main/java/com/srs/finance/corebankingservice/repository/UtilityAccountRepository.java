package com.srs.finance.corebankingservice.repository;

import com.srs.finance.corebankingservice.model.entity.UtilityAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilityAccountRepository extends JpaRepository<UtilityAccountEntity,Long> {
    Optional<UtilityAccountEntity> findByProviderName(String provider);
}
