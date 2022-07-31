package com.user.api.userdemo.dto;


import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private long id;
    private String name;
    private Integer age;
    private String address1;
    private String address2;

}
