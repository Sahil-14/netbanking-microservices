package com.src.finance.service;


import com.src.finance.exception.*;
import com.src.finance.model.dto.Status;
import com.src.finance.model.dto.User;
import com.src.finance.model.dto.UserUpdateRequest;
import com.src.finance.model.entity.UserEntity;
import com.src.finance.model.mapper.UserMapper;
import com.src.finance.model.repository.UserRepository;
import com.src.finance.model.rest.response.UserResponse;
import com.src.finance.service.rest.BankingCoreRestClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final KeycloakUserService keycloakUserService;
    private final UserRepository userRepository;
    private final BankingCoreRestClient bankingCoreRestClient;

    private UserMapper userMapper = new UserMapper();

    public User createUser(User user) {

        List<UserRepresentation> userRepresentations = keycloakUserService.readUserByEmail(user.getEmail());
        if (userRepresentations.size() > 0) {
            throw new UserAlreadyRegisteredException("This email already registered as a user. Please check and retry.", GlobalErrorCode.ERROR_EMAIL_REGISTERED);
        }

        UserResponse userResponse = bankingCoreRestClient.readUser(user.getIdentification());

        if (userResponse.getId() != null) {

            if (!userResponse.getEmail().equals(user.getEmail())) {
                throw new InvalidEmailException("Incorrect email. Please check and retry.", GlobalErrorCode.ERROR_INVALID_EMAIL);
            }

            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setEmail(userResponse.getEmail());
            userRepresentation.setEmailVerified(false);
            userRepresentation.setEnabled(false);
            userRepresentation.setUsername(userResponse.getEmail());

            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setValue(user.getPassword());
            credentialRepresentation.setTemporary(false);
            userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

            Integer userCreationResponse = keycloakUserService.createUser(userRepresentation);

            if (userCreationResponse == 201) {
                log.info("User created under given username {}", user.getEmail());

                List<UserRepresentation> userRepresentations1 = keycloakUserService.readUserByEmail(user.getEmail());
                user.setAuthId(userRepresentations1.get(0).getId());
                user.setStatus(Status.PENDING);
                user.setIdentification(userResponse.getIdentificationNumber());
                UserEntity save = userRepository.save(userMapper.convertToEntity(user));
                return userMapper.convertToDto(save);
            }

        }

        throw new InvalidBankingUserException("We couldn't find user under given identification. Please check and retry", GlobalErrorCode.ERROR_USER_NOT_FOUND_UNDER_NIC);

    }

    public List<User> readUsers(Pageable pageable) {
        Page<UserEntity> allUsersInDb = userRepository.findAll(pageable);
        List<User> users = userMapper.convertToDtoList(allUsersInDb.getContent());
        users.forEach(user -> {
            UserRepresentation userRepresentation = keycloakUserService.readUser(user.getAuthId());
            user.setId(user.getId());
            user.setEmail(userRepresentation.getEmail());
            user.setIdentification(user.getIdentification());
        });
        return users;
    }

    public User readUser(Long userId) {
        return userMapper.convertToDto(userRepository.findById(userId).orElseThrow(EntityNotFoundException::new));
    }

    public User updateUser(Long id, UserUpdateRequest userUpdateRequest) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (userUpdateRequest.getStatus() == Status.APPROVED) {
            UserRepresentation userRepresentation = keycloakUserService.readUser(userEntity.getAuthId());
            userRepresentation.setEnabled(true);
            userRepresentation.setEmailVerified(true);
            keycloakUserService.updateUser(userRepresentation);
        }

        userEntity.setStatus(userUpdateRequest.getStatus());
        return userMapper.convertToDto(userRepository.save(userEntity));
    }


}
