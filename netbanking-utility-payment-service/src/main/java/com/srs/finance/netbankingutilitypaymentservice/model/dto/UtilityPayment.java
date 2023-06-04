package com.srs.finance.netbankingutilitypaymentservice.model.dto;

import com.srs.finance.netbankingutilitypaymentservice.model.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UtilityPayment {
    private Long providerId;
    private BigDecimal amount;
    private String referenceNumber;
    private String account;
    private TransactionStatus status;
}
