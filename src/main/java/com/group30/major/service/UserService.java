package com.group30.major.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group30.major.model.User;
import com.group30.major.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @Transactional
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}