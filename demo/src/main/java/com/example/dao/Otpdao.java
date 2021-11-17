package com.example.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.entities.Otp;
@Repository
public interface Otpdao extends JpaRepository<Otp,String>
{
  
  
}