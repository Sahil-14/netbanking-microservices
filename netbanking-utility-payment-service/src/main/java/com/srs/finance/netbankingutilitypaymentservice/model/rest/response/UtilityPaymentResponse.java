package com.srs.finance.netbankingutilitypaymentservice.model.rest.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UtilityPaymentResponse {
    private String message;
    private String transactionId;
}
