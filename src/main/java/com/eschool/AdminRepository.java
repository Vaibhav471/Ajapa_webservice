package com.eschool;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {

	Admin findByIdentifierAndPassword(String identifier, String password);
	
	List<Admin> findAll();
	
	Admin findByIdentifier(String identifier);

}
