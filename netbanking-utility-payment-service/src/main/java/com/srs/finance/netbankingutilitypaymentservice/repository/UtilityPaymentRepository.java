package com.srs.finance.netbankingutilitypaymentservice.repository;

import com.srs.finance.netbankingutilitypaymentservice.model.dto.UtilityPayment;
import com.srs.finance.netbankingutilitypaymentservice.model.entity.UtilityPaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilityPaymentRepository extends JpaRepository<UtilityPaymentEntity, UtilityPayment> {
}
