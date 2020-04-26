package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TestController {

//	@PostMapping("/org")
//	public ResponseEntity<?> createOrganization() {
//
//		orgService.postData();
//
//		return ResponseEntity.status(HttpStatus.CREATED).body("Data posted successfully");
//	}
//	
//	
//	@GetMapping("/org")
//	public ResponseEntity<?> getOrganization(){
//		return ResponseEntity.status(HttpStatus.CREATED).body(orgService.getOrganization());
//	}

}
