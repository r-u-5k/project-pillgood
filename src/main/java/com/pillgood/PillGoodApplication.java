package com.pillgood;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pillgood.service.InsertService;

@SpringBootApplication
public class PillGoodApplication implements CommandLineRunner{
	
	@Autowired
	InsertService insertService;

    public static void main(String[] args) {
        SpringApplication.run(PillGoodApplication.class, args);
        
    }
    
    @Override
    public void run(String... args) throws Exception {
    	//insertService.insertCategory();
    	//insertService.insertItem();
    	//insertService.insertAdminUser();
        
    }
}