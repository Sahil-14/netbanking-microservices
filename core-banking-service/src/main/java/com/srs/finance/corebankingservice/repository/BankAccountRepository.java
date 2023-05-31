package com.srs.finance.corebankingservice.repository;

import com.srs.finance.corebankingservice.model.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity,Long> {
    Optional<BankAccountEntity> findByNumber(String accountNumber);
}
