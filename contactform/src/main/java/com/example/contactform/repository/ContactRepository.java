package com.example.contactform.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.contactform.entity.ContactForm;

public interface ContactRepository extends JpaRepository<ContactForm, Integer>{
	Page<ContactForm> findByOwnerId(Integer ownerId, Pageable pageable);
}
