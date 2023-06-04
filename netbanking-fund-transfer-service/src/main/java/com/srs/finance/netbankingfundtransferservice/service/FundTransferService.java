package com.srs.finance.netbankingfundtransferservice.service;

import com.srs.finance.netbankingfundtransferservice.model.TransactionStatus;
import com.srs.finance.netbankingfundtransferservice.model.dto.FundTransfer;
import com.srs.finance.netbankingfundtransferservice.model.dto.request.FundTransferRequest;
import com.srs.finance.netbankingfundtransferservice.model.dto.response.FundTransferResponse;
import com.srs.finance.netbankingfundtransferservice.model.entity.FundTransferEntity;
import com.srs.finance.netbankingfundtransferservice.model.mapper.FundTransferMapper;
import com.srs.finance.netbankingfundtransferservice.model.repository.FundTransferRepository;
import com.srs.finance.netbankingfundtransferservice.service.rest.client.BankingCoreFeignClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class FundTransferService {
    private final FundTransferRepository fundTransferRepository;
    private final BankingCoreFeignClient bankingCoreFeignClient;

    private FundTransferMapper mapper = new FundTransferMapper();

    public FundTransferResponse fundTransfer(FundTransferRequest request) {
        log.info("Sending fund transfer request {}" + request.toString());

        FundTransferEntity entity = new FundTransferEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setStatus(TransactionStatus.PENDING);
        FundTransferEntity optFundTransfer = fundTransferRepository.save(entity);

        FundTransferResponse fundTransferResponse = bankingCoreFeignClient.fundTransfer(request);
        optFundTransfer.setTransactionReference(fundTransferResponse.getTransactionId());
        optFundTransfer.setStatus(TransactionStatus.SUCCESS);
        fundTransferRepository.save(optFundTransfer);

        fundTransferResponse.setMessage("Fund Transfer Successfully Completed");
        return fundTransferResponse;

    }

    public List<FundTransfer> readAllTransfers(Pageable pageable) {
        return mapper.convertToDtoList(fundTransferRepository.findAll(pageable).getContent());
    }
}
