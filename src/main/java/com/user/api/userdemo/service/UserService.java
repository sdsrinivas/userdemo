package com.user.api.userdemo.service;

import com.user.api.userdemo.dto.UserDto;
import com.user.api.userdemo.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto postDto);

    UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
}