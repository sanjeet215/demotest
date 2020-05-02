package com.example.demo.model.payload.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.model.State;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.CountryRepository;
import com.example.demo.repository.StateRepository;

@Service
public class CityService {

	private static final Logger logger = LoggerFactory.getLogger(CityService.class);

	@Autowired
	CountryRepository countryRepo;

	@Autowired
	StateRepository stateRepo;

	@Autowired
	CityRepository cityRepo;

	public List<Country> getCountries() {
		return countryRepo.findAll();
	}

	public List<State> getStatebyCountry(Long countryId) {

		logger.debug("Extracting State List from country");

		Country country = countryRepo.findById(countryId)
				.orElseThrow(() -> new ResourceNotFoundException("Country not found by requested Id"));

		return stateRepo.findByCountry(country);
	}

	public List<City> getCitiListbyState(Long stateId) {
		logger.debug("Extracting CityList");

		State state = stateRepo.findById(stateId).orElseThrow(() -> new ResourceNotFoundException("Invalid state Id"));
		return cityRepo.findByState(state);
	}
}
