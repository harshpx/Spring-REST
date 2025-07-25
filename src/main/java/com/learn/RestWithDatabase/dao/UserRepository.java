package com.learn.RestWithDatabase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.RestWithDatabase.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findByUsername(String username);
}