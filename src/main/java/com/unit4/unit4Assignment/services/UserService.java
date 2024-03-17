package com.unit4.unit4Assignment.services;

import com.unit4.unit4Assignment.dtos.UserDto;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    List<String> addUser(UserDto userDto);

    @Transactional
    List<String> addAdmin(UserDto userDto);

    List<String> userLogin(UserDto userDto);
}
