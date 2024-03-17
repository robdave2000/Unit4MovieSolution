package com.unit4.unit4Assignment.controllers;

import com.unit4.unit4Assignment.dtos.UserDto;
import com.unit4.unit4Assignment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register/user")
    public List<String> addUser(@RequestBody UserDto userDto)
    {
        String passHash = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addUser(userDto);
    }

    @PostMapping("/register/admin")
    public List<String> addAdmin(@RequestBody UserDto userDto)
    {
        String passHash = bCryptPasswordEncoder.encode(userDto.getPassword());
        userDto.setPassword(passHash);
        return userService.addAdmin(userDto);
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto)
    {
        return userService.userLogin(userDto);
    }
}
