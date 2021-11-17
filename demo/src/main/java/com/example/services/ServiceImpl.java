package com.example.services;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.dao.Otpdao;
import com.example.entities.Otp;

@Service
public class ServiceImpl implements EmailService {

	@Autowired
	private Otpdao repo;
	
	@Autowired
	private JavaMailSender mailSender;
	
	 private static final long OTP_VALID_DURATION =  60 * 1000;
	 
	 Logger logger=LoggerFactory.getLogger(ServiceImpl.class);
	 
	@Override
	public int generateOtp(String to) {
		// TODO Auto-generated method stub
		int otp=(int)(Math.random()*900000)+100000;
		String finalOTP=String.valueOf(otp);
		Otp newOtp=new Otp();
		newOtp.setOtp(otp);
		newOtp.setEmailId(to);
		SimpleMailMessage message=new SimpleMailMessage();
		message.setTo(to);
		message.setText(finalOTP);
		mailSender.send(message);
	    newOtp.setGeneratedDate(new Date());	
	    repo.save(newOtp);
		logger.info("Mail sent sucesfully to "+to+" otp is "+otp);
		return otp;
	}

	@Override
	public String validateOtp(String otp, String emailID) {
		// TODO Auto-generated method stub
		Otp userotp=repo.findById(emailID).orElse(null);
		long currentTimeInMillis = System.currentTimeMillis();
		
		     if(userotp==null)
		     {
		    	 logger.debug("No such otp exists -otp is null");
				return "OtpInvalid";
				
		    }
		    long otpRequestedTimeInMillis = userotp.getGeneratedDate().getTime();
			
			if(userotp.getOtp()==Integer.parseInt(otp)&& (otpRequestedTimeInMillis + OTP_VALID_DURATION > currentTimeInMillis))
			{
				logger.info("Otp has been verified!");
				return "ValidOtp";
			}
			
			else if(otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis)
			{
				
				repo.delete(userotp);
				return "expiredotp";
			}
			else
			{
				logger.debug("Some other error occured");
				return "OtpInvalid";
			}
			
	}

}
