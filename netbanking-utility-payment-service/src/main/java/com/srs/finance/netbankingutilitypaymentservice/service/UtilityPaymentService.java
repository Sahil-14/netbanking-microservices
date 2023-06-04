package com.srs.finance.netbankingutilitypaymentservice.service;


import com.srs.finance.netbankingutilitypaymentservice.model.TransactionStatus;
import com.srs.finance.netbankingutilitypaymentservice.model.dto.UtilityPayment;
import com.srs.finance.netbankingutilitypaymentservice.model.entity.UtilityPaymentEntity;
import com.srs.finance.netbankingutilitypaymentservice.model.mapper.UtilityPaymentMapper;
import com.srs.finance.netbankingutilitypaymentservice.model.rest.request.UtilityPaymentRequest;
import com.srs.finance.netbankingutilitypaymentservice.model.rest.response.UtilityPaymentResponse;
import com.srs.finance.netbankingutilitypaymentservice.repository.UtilityPaymentRepository;
import com.srs.finance.netbankingutilitypaymentservice.service.rest.BankingCoreRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UtilityPaymentService {
    private final UtilityPaymentRepository utilityPaymentRepository;
    private final BankingCoreRestClient bankingCoreRestClient;

    private UtilityPaymentMapper utilityPaymentMapper = new UtilityPaymentMapper();

    public UtilityPaymentResponse utilPayment(UtilityPaymentRequest paymentRequest) {
        log.info("Utility payment processing {}", paymentRequest.toString());

        UtilityPaymentEntity entity = new UtilityPaymentEntity();
        BeanUtils.copyProperties(paymentRequest, entity);
        entity.setStatus(TransactionStatus.PROCESSING);
        UtilityPaymentEntity optUtilPayment = utilityPaymentRepository.save(entity);

        UtilityPaymentResponse utilityPaymentResponse = bankingCoreRestClient.utilityPayment(paymentRequest);
        log.info("Transaction response {}", utilityPaymentResponse.toString());

        optUtilPayment.setStatus(TransactionStatus.SUCCESS);
        optUtilPayment.setTransactionId(utilityPaymentResponse.getTransactionId());
        utilityPaymentRepository.save(optUtilPayment);

        return UtilityPaymentResponse.builder().message("Utility Payment Successfully Processed").transactionId(utilityPaymentResponse.getTransactionId()).build();
    }

    public List<UtilityPayment> readPayments(Pageable pageable) {
        Page<UtilityPaymentEntity> allUtilPayments = utilityPaymentRepository.findAll(pageable);
        return utilityPaymentMapper.convertToDtoList(allUtilPayments.getContent());
    }
}
