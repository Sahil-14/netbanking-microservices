package com.srs.finance.corebankingservice.controller;

import com.srs.finance.corebankingservice.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;

    @GetMapping(value = "/{identification}")
    public ResponseEntity readUser(@PathVariable("identification") String identification) {
        return ResponseEntity.ok(userService.readUser(identification));
    }

    @GetMapping
    public ResponseEntity readUsers(Pageable pageable) {
        return ResponseEntity.ok(userService.readUsers(pageable));
    }
}
