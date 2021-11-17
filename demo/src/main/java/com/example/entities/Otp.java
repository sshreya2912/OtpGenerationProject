package com.example.entities;


import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "userOTP")
public class Otp{

	
	private int otp;
	@Id
	private String emailId;
	private Date generatedDate;
	
	public Otp(int otp, String emailId, Date generatedDate) {
		super();
		this.otp = otp;
		this.emailId = emailId;
		this.generatedDate = generatedDate;
	}

	public Otp() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Date getGeneratedDate() {
		return generatedDate;
	}

	public void setGeneratedDate(Date generatedDate) {
		this.generatedDate = generatedDate;
	}

	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getOtp() {
		return otp;
	}
	public void setOtp(int otp) {
		this.otp = otp;
	}
	
	@Override
	public String toString() {
		return "UserDetails [emailId=" + emailId + ", otp=" + otp + "]";
	}

	
}
