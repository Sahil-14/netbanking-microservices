package com.srs.finance.netbankingfundtransferservice.model.dto.response;

import lombok.Data;

@Data
public class FundTransferResponse {
    private String message;
    private String transactionId;
}
