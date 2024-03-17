package com.unit4.unit4Assignment.services;

import com.unit4.unit4Assignment.dtos.UserDto;
import com.unit4.unit4Assignment.entities.User;
import com.unit4.unit4Assignment.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto)
    {
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        user.setAdmin(false);
        userRepository.saveAndFlush(user);
        response.add("http://localhost:8080/login.html");
        return response;
    }

    @Override
    @Transactional
    public List<String> addAdmin(UserDto userDto)
    {
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        user.setAdmin(true);
        userRepository.saveAndFlush(user);
        response.add("Admin added");
        return response;
    }

    @Override
    public List<String> userLogin(UserDto userDto)
    {
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if (userOptional.isPresent())
        {
            if (bCryptPasswordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword()) && userOptional.get().isAdmin())
            {
                response.add("http://localhost:8080/adminHome.html");
            }
            else if (bCryptPasswordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword()))
            {
                response.add("http://localhost:8080/userHome.html");
            }
            else
            {
                response.add("Username or password incorrect");
            }
        }
        else
        {
            response.add("Username or password incorrect");
        }
        return response;
    }
}
