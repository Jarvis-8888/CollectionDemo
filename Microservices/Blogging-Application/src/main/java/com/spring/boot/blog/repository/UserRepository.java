package com.spring.boot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.boot.blog.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
