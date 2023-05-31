package com.srs.finance.corebankingservice.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UtilityPaymentResponse {
    private String message;
    private String transactionId;
}
