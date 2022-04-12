package com.reto.demo.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    void saveUser(MultipartFile file);
}
