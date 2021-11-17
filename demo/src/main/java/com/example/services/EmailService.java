package com.example.services;




public interface EmailService{

	public int generateOtp(String key);

	public String validateOtp(String otp, String emailID);
	
}
