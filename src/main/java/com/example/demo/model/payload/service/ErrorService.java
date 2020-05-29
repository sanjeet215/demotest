package com.example.demo.model.payload.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomErrors;
import com.example.demo.repository.CustomErrorRepository;

@Service
public class ErrorService {

	private static final Logger logger = LoggerFactory.getLogger(ErrorService.class);

	@Autowired
	CustomErrorRepository errorRepo;

	public void posterrorMessage(CustomErrors error) {
		errorRepo.save(error);
	}
}
