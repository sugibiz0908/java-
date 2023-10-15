package com.example.contactform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contactform.entity.Account;


public interface RoleRepository extends JpaRepository<Account, Integer> {

}