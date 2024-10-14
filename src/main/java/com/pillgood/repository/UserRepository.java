package com.pillgood.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pillgood.entity.User;
public interface UserRepository extends JpaRepository<User, Long>{

	User findByPhone(String phone); 
	User findByEmailAndPassword(String email, String password);
	User findByEmailAndDeleted(String email, boolean deleted);
	User findByEmailAndName(String email, String name);
	User findByEmail(String email);



}
