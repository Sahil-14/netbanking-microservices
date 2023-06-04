package com.srs.finance.netbankingutilitypaymentservice.model.entity;

import com.srs.finance.netbankingutilitypaymentservice.model.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "utility_payment")
public class UtilityPaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long providerId;
    private BigDecimal amount;
    private String referenceNumber;
    private String account;
    private String transactionId;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

}