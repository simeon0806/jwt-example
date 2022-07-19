package com.jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/access")
public class BaseApiController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole(T(com.jwt.domain.constants.RoleConstants).ROLE_ADMIN)")
    public ResponseEntity<String> getMessageForAdmins() {
        return new ResponseEntity<>("Only admins!", HttpStatus.OK);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole(T(com.jwt.domain.constants.RoleConstants).ROLE_USER)")
    public ResponseEntity<String> getMessageForUsers() {
        return new ResponseEntity<>("Only users!", HttpStatus.OK);
    }

}
