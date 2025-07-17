package com.learn.RestWithDatabase.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learn.RestWithDatabase.entity.User;

@Repository
public interface UserRepositroy extends JpaRepository<User, Integer> {
}