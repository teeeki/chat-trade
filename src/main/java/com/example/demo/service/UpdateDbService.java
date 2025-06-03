package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UpdateDbService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ItemRepository itemRepository;

    public User updateUserVerify(String username) {
        User user = userRepository.findByUsername(username).get();
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        user.setIsVerify(true);

        return userRepository.save(user);
    }

}
