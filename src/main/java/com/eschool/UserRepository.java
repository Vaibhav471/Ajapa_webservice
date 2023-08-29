package com.eschool;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByEmailAndPassword(String email, String password);
	
	User findByEmail(String email);
	
	
	User  findById(int id);
	
}

