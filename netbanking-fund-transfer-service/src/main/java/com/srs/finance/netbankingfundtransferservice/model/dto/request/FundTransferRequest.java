package com.srs.finance.netbankingfundtransferservice.model.dto.request;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class FundTransferRequest {
    private String fromAccount;
    private String toAccount;
    private BigDecimal amount;
    private String authID;
}
