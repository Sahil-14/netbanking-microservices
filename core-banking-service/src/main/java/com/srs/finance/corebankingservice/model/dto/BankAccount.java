package com.srs.finance.corebankingservice.model.dto;

import com.srs.finance.corebankingservice.model.AccountStatus;
import com.srs.finance.corebankingservice.model.AccountType;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class BankAccount {

    private Long id;
    private String number;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal availableBalance;
    private BigDecimal actualBalance;
    private User user;
}
