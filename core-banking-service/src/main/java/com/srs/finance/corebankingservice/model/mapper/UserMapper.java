package com.srs.finance.corebankingservice.model.mapper;

import com.srs.finance.corebankingservice.model.dto.User;
import com.srs.finance.corebankingservice.model.entity.UserEntity;
import org.springframework.beans.BeanUtils;

public class UserMapper extends BaseMapper<UserEntity, User>{
    private BankAccountMapper bankAccountMapper = new BankAccountMapper();

    @Override
    public UserEntity convertToEntity(User dto, Object... args) {
        UserEntity entity = new UserEntity();
        if (dto != null) {
            BeanUtils.copyProperties(dto, entity, "accounts");
            entity.setAccounts(bankAccountMapper.convertToEntityList(dto.getBankAccounts()));
        }
        return entity;
    }

    @Override
    public User convertToDto(UserEntity entity, Object... args) {
        User dto = new User();
        if (entity != null) {
            BeanUtils.copyProperties(entity, dto, "accounts");
            dto.setBankAccounts(bankAccountMapper.convertToDtoList(entity.getAccounts()));
        }
        return dto;
    }
}
