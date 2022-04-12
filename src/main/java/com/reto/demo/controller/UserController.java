package com.reto.demo.controller;

import com.reto.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Slf4j
@RequestMapping("/v1/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/loadCsv", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity loadCsv(@RequestParam("csv") MultipartFile file) {
        userService.saveUser(file);
        return ResponseEntity.ok().build();
    }
}
