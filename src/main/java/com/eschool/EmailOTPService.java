package com.eschool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eschool.beans.EmailOTP;

@Service
public class EmailOTPService {

	@Autowired
	private final EmailOTPRepository otpsRepository;

    
    public EmailOTPService(EmailOTPRepository otpRepository) {
        this.otpsRepository = otpRepository;
    }

    public void saveOTP(EmailOTP otp) {
        otpsRepository.save(otp);
    }

    public int getLatestOTPByEmail(String email) {
        List<EmailOTP> otpList = otpsRepository.findByEmailOrderByIdDesc(email);
        if(otpList!=null && otpList.size()!=0)
        {
        	EmailOTP otp=otpList.get(0);
        	return otp.getOtp();
        }
        return -1;
    }
}
