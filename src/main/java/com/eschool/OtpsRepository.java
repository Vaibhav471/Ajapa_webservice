package com.eschool;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eschool.beans.Otps;

public interface OtpsRepository extends JpaRepository<Otps, Integer> {
    List<Otps> findByPnoOrderByIdDesc(String pno); 
    
}
