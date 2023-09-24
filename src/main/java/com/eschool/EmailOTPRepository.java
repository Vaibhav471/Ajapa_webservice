package com.eschool;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eschool.beans.EmailOTP;

public interface EmailOTPRepository extends JpaRepository<EmailOTP, Integer>{
    List<EmailOTP> findByEmailOrderByIdDesc(String email); 

}
