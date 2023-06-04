package com.srs.finance.netbankingfundtransferservice.model.repository;

import com.srs.finance.netbankingfundtransferservice.model.entity.FundTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundTransferRepository extends JpaRepository<FundTransferEntity, Long> {
}
