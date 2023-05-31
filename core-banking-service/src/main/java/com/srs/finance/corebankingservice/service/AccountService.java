package com.srs.finance.corebankingservice.service;

import com.srs.finance.corebankingservice.exception.EntityNotFoundException;
import com.srs.finance.corebankingservice.model.dto.BankAccount;
import com.srs.finance.corebankingservice.model.dto.UtilityAccount;
import com.srs.finance.corebankingservice.model.entity.BankAccountEntity;
import com.srs.finance.corebankingservice.model.entity.UtilityAccountEntity;
import com.srs.finance.corebankingservice.model.mapper.BankAccountMapper;
import com.srs.finance.corebankingservice.model.mapper.UtilityAccountMapper;
import com.srs.finance.corebankingservice.repository.BankAccountRepository;
import com.srs.finance.corebankingservice.repository.UtilityAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AccountService {

    private BankAccountMapper bankAccountMapper = new BankAccountMapper();
    private UtilityAccountMapper utilityAccountMapper = new UtilityAccountMapper();

    private final BankAccountRepository bankAccountRepository;
    private final UtilityAccountRepository utilityAccountRepository;

    public BankAccount readBankAccount(String accountNumber) {
        BankAccountEntity entity = bankAccountRepository.findByNumber(accountNumber).orElseThrow(EntityNotFoundException::new);
        return bankAccountMapper.convertToDto(entity);
    }

    public UtilityAccount readUtilityAccount(String provider) {
        UtilityAccountEntity utilityAccountEntity = utilityAccountRepository.findByProviderName(provider).orElseThrow(EntityNotFoundException::new);
        return utilityAccountMapper.convertToDto(utilityAccountEntity);
    }

    public UtilityAccount readUtilityAccount(Long id){
        return utilityAccountMapper.convertToDto(utilityAccountRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }
}
