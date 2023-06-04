package com.srs.finance.netbankingutilitypaymentservice.model.rest.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountResponse {
    private Long number;
    private BigDecimal actualBalance;
    private Long id;
    private String type;
    private String status;
    private BigDecimal availableBalance;
}
