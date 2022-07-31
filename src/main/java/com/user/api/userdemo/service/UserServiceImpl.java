package com.user.api.userdemo.service;

import com.github.javafaker.Faker;
import com.user.api.userdemo.dto.UserDto;
import com.user.api.userdemo.dto.UserResponse;
import com.user.api.userdemo.model.User;
import com.user.api.userdemo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        dataSetUp();
    }

    @Override
    public UserDto createUser(UserDto userDto) {

        // convert DTO to entity
        User user = mapToEntity(userDto);
        User newUser = userRepository.save(user);

        // convert entity to DTO
        UserDto userResponse = mapToDTO(newUser);
        return userResponse;
    }

    @Override
    public UserResponse getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<User> users = userRepository.findAll(pageable);

        // get content for page object
        List<User> listOfPosts = users.getContent();

        List<UserDto> content= listOfPosts.stream().map(user -> mapToDTO(user)).collect(Collectors.toList());

        UserResponse userResponse = new UserResponse();
        userResponse.setContent(content);
        userResponse.setPageNo(users.getNumber());
        userResponse.setPageSize(users.getSize());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setLast(users.isLast());

        return userResponse;
    }

    // convert Entity into DTO
    private UserDto mapToDTO(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
        userDto.setAddress1(user.getAddress1());
        userDto.setAddress2(user.getAddress2());
        return userDto;
    }

    // convert DTO to entity
    private User mapToEntity(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setAddress1(userDto.getAddress1());
        user.setAddress2(userDto.getAddress2());
        return user;
    }


    private void dataSetUp(){
        for(int i=0; i<100; i++){
            User user = new User();
            user.setName("abcdef");
            user.setAge(30);
            user.setAddress1("address 1");
            user.setAddress2("address 2");
            userRepository.save(user);
        }
        userRepository.flush();
    }
}

