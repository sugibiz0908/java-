package com.example.contactform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contactform.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {	
	public User findByEmail(String email);
}
