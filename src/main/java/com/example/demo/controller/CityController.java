package com.example.demo.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.MessageConstants;
import com.example.demo.model.payload.response.ApiResponse;
import com.example.demo.model.payload.service.CityService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CityController {

	private static final Logger logger = LoggerFactory.getLogger(CityController.class);

	@Autowired
	CityService cityService;

	/* Get All Country List for address */
	@GetMapping("/city")
	public ResponseEntity<ApiResponse> getCountries() {
		return ResponseEntity.status(HttpStatus.OK).body(
				new ApiResponse(HttpStatus.OK.value(), MessageConstants.CNT_EXTRACTED, cityService.getCountries()));
	}

	@GetMapping("/city/{countryId}")
	public ResponseEntity<ApiResponse> getStatesbyCountry(@Valid @PathVariable("countryId") Long countryId) {

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),
				MessageConstants.STATE_EXTRACTED, cityService.getStatebyCountry(countryId)));
	}

	/* Get List of Cities by State id */

	@GetMapping("/state/{stateId}")
	public ResponseEntity<ApiResponse> getCitiesbyState(@Valid @PathVariable("stateId") Long stateId) {

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),
				MessageConstants.CITY_EXTRACTED, cityService.getCitiListbyState(stateId)));
	}
}
