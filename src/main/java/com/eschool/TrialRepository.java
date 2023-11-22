package com.eschool;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.Trial;
import com.eschool.beans.User;

public interface TrialRepository extends CrudRepository<Trial, Integer> { 
	List<Trial> findAll();
}
