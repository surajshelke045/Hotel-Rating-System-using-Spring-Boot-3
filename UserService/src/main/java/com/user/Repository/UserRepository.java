package com.user.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.Entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
