package com.eschool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eschool.beans.EmailOTP;

@Service
public class EmailOTPService {

	private final EmailOTPRepository otpsRepository;

    @Autowired
    public EmailOTPService(EmailOTPRepository otpRepository) {
        this.otpsRepository = otpRepository;
    }

    public void saveOTP(EmailOTP otp) {
        otpsRepository.save(otp);
    }

    public int getLatestOTPByEmail(String pno) {
        List<EmailOTP> otpList = otpsRepository.findByEmailOrderByIdDesc(pno);
        if(otpList!=null && otpList.size()!=0)
        {
        	EmailOTP otp=otpList.get(0);
        	return otp.getOtp();
        }
        return -1;
    }
}
