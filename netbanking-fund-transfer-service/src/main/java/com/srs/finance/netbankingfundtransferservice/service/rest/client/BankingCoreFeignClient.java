package com.srs.finance.netbankingfundtransferservice.service.rest.client;

import com.srs.finance.netbankingfundtransferservice.configuration.CustomFeignClientConfiguration;
import com.srs.finance.netbankingfundtransferservice.model.dto.request.FundTransferRequest;
import com.srs.finance.netbankingfundtransferservice.model.dto.response.AccountResponse;
import com.srs.finance.netbankingfundtransferservice.model.dto.response.FundTransferResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "core-banking-service", configuration = CustomFeignClientConfiguration.class)
public interface BankingCoreFeignClient {

    @RequestMapping(path = "/api/v1/account/bank-account/{account_number}", method = RequestMethod.GET)
    AccountResponse readAccount(@PathVariable("account_number") String accountNumber);

    @RequestMapping(path = "/api/v1/transaction/fund-transfer", method = RequestMethod.POST)
    FundTransferResponse fundTransfer(@RequestBody FundTransferRequest fundTransferRequest);

}