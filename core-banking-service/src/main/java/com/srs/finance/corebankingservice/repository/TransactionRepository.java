package com.srs.finance.corebankingservice.repository;

import com.srs.finance.corebankingservice.model.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Long> {
}
