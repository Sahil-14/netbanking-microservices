package com.srs.finance.corebankingservice.repository;

import com.srs.finance.corebankingservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByIdentificationNumber(String identificationNumber);
}
