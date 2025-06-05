package com.vitdo82.paa.serviceapi.user.service;

import com.vitdo82.paa.serviceapi.user.repository.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public List<User> getUsers() {
        return List.of();
    }
}
