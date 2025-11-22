package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	
	public UserEntity findByEmailAndPassword(String email, String password);
	public UserEntity findByName(String name);

}
