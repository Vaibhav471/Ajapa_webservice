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
	List<User> findByIsAdminTrue();
	User findByMobileNum(String mobileNum);
	@Query("SELECT u FROM User u WHERE (u.email = :identifier OR u.mobileNum = :identifier) AND u.password = :password")
    User findByEmailOrMobileNumberAndPassword(@Param("identifier") String identifier, @Param("password") String password);
	@Query("SELECT u FROM User u WHERE (u.email = :identifier OR u.mobileNum = :identifier)")
    User findByEmailOrMobileNumber(@Param("identifier") String identifier);
	long countByStatus(int status);
	@Query("SELECT u.fullName FROM User u WHERE u.id = :id")
    String findUserNameByUserId(int id);
	int findFamilyIdByEmail(String email);
	@Query("SELECT u.id FROM User u WHERE u.email = :email")
    int findIdByEmail(@Param("email") String email);
	List<User> findUsersByFamilyId(int id);
	String findMobileNumById(int id);
	
	@Query("SELECT u FROM User u where status=1 order by u.familyId,u.country,u.state,u.city")
    List<User> getAllUsersOrderByFamilyIdCountryStateCity();
	
	@Query("SELECT u FROM User u where u.status=:status order by u.familyId,u.country,u.state,u.city")
    List<User> getAllUsersByStatusOrderByFamilyIdCountryStateCity(@Param("status") int status);
	
}

