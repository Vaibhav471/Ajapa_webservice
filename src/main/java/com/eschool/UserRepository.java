package com.eschool;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eschool.beans.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByEmailAndPassword(String email, String password);
	User findByEmail(String email);
	List<User> findAll();
	User  findById(int id);
	List<User> findUsersByStatus(int status);
	User findByMobileNum(String mobileNum);
	
	@Query("SELECT u FROM User u WHERE (u.email = :identifier OR u.mobileNum = :identifier) AND u.password = :password")
    User findByEmailOrMobileNumberAndPassword(@Param("identifier") String identifier, @Param("password") String password);

	long countByStatus(int status);
	
	@Query("SELECT u.fullName FROM User u WHERE u.id = :id")
    String findUserNameByUserId(int id);
	
	int findFamilyIdByEmail(String email);
	
	@Query("SELECT u.id FROM User u WHERE u.email = :email")
    int findIdByEmail(@Param("email") String email);
	List<User> findUsersByFamilyId(int id);
	
	String findMobileNumById(int id);
}

