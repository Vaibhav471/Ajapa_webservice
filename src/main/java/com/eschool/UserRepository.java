package com.eschool;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eschool.beans.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);
	User  findById(int id);
	List<User> findUsersByStatus(int status);
	User findByMobileNum(String mobileNum);
	
	@Query("SELECT u FROM User u WHERE (u.email = :identifier OR u.mobileNum = :identifier) AND u.password = :password")
    User findByEmailOrMobileNumberAndPassword(@Param("identifier") String identifier, @Param("password") String password);
}

