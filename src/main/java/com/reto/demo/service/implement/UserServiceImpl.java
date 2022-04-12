package com.reto.demo.service.implement;

import com.reto.demo.entity.User;
import com.reto.demo.exception.BusinessException;
import com.reto.demo.repository.UserRepository;
import com.reto.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Async
    @Override
    public void saveUser(MultipartFile file) {
        log.info("Init method saveUser");
        List<User> users = parseCsvFile(file);
        log.info("List of users" + users);
        userRepository.saveAll(verifyUser(users));
        log.info("End method saveUser");
    }

    private List<User> verifyUser(List<User> lstUser) {
        log.info("Init method verifyUser");
        return lstUser.stream().map(user -> {
            User userAux = new User();
            userAux.setDni(user.getDni());
            userAux.setName(user.getName());
            userAux.setLastName(user.getLastName());
            userRepository.getUserByDni(user.getDni()).ifPresent(userDni -> userAux.setId(userDni.getId()));
            return userAux;
        }).collect(Collectors.toList());
    }

    private List<User> parseCsvFile(MultipartFile file) {
        log.info("Init method parseCsvFile");
        List<User> lstUser = new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    User user = new User();
                    user.setName(data[0]);
                    user.setLastName(data[1]);
                    user.setDni(data[2]);
                    lstUser.add(user);
                }
                return lstUser;
            }
        } catch (IOException e) {
            log.error("Error parseCsvFile", e);
            throw new BusinessException(422, "Error en el Archivo");
        }
    }


}
