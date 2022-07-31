package com.user.api.userdemo.repository;

import com.user.api.userdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}