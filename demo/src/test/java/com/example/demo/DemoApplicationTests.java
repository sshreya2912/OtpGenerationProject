package com.example.demo;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;

import static com.jcabi.matchers.RegexMatchers.matchesPattern;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.dao.Otpdao;
import com.example.entities.Otp;
import com.example.services.EmailService;

@SpringBootTest
class DemoApplicationTests {

	
	@Autowired
	public Otpdao repository;
	@Autowired
	public EmailService es;
	@Test
	void contextLoads() {
	}
	@Test
	void testOTPLength()
	{
		int expectedResult=6;
		int actualResult=String.valueOf(es.generateOtp("shreya.18bcs1190@abes.ac.in")).length();
		assertThat(expectedResult).isEqualTo(actualResult);
		
	}
	@Test
	void testValidateOtp()
	{
		String s=es.validateOtp("909809","shreya.18bcs1190@abes.ac.in");
		assertThat(s).isNotNull();
	}
	@Test
	void validateOtpLength()
	{
		String otp=Integer.toString(es.generateOtp("shreya.18bcs1190@abes.ac.in"));
		assertEquals(6, otp.length());
	}
	@Test
	void checkDigit()
	{
		String expectedOtp="[0-9]{6}";
		
		String otp=Integer.toString(es.generateOtp("shreya.18bcs1190@abes.ac.in"));
		
		assertTrue(Pattern.matches(expectedOtp, otp));
	}
	
	@Test 
	void validateOtp1()
	{
		String s=es.validateOtp("000000", "shreya.18bcs1190@abes.ac.in");
		
		assertEquals("OtpInvalid",s);
	}
	
	@Test
	void validateOtp2()
	{
		String s=es.validateOtp("000000", "xyz@gmail.com");
		assertEquals("OtpInvalid",s);
		
	}
//	@Test 
//	void validateOtp2()
//	{
//		String generatedOtp=es.generateOtp("shreya.18bcs1190@abes.ac.in").toString();
//		String s=es.validateOtp(generatedOtp, "shreya.18bcs1190@abes.ac.in");
//		
//		assertEquals("OtpInvalid",s);
//	}
	
	
}
