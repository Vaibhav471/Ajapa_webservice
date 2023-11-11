package com.eschool;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eschool.beans.Otps;
@Service
public class OtpsService {
	private final OtpsRepository otpsRepository;    
    public OtpsService(OtpsRepository otpRepository) {
        this.otpsRepository = otpRepository;
    }
    public void saveOTP(Otps otp) {
        otpsRepository.save(otp);
    }
    public int getLatestOTPByPno(String pno) {
        List<Otps> otpList = otpsRepository.findByPnoOrderByIdDesc(pno);
        if(otpList!=null && otpList.size()!=0)
        {
        	Otps otp=otpList.get(0);
        	return otp.getOtp();
        }
        return -1;
    }
}
