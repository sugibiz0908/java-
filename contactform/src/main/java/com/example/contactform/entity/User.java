package com.example.contactform.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "contactuser")
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "message")
    private String message;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Account role;

	@Column(name = "password")
    private String password; 
	
	@Column(name = "enabled")
    private Boolean enabled;
}
