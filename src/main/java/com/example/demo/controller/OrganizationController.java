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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constants.MessageConstants;
import com.example.demo.model.Address;
import com.example.demo.model.Organization;
import com.example.demo.model.payload.request.AddressRequest;
import com.example.demo.model.payload.request.OrganizationAddressRequest;
import com.example.demo.model.payload.request.OrganizationRequest;
import com.example.demo.model.payload.response.ApiResponse;
import com.example.demo.model.payload.service.OrganizationService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class OrganizationController {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

	@Autowired
	OrganizationService orgService;

	// Request to get all organizations
//	@GetMapping("/org")
//	// @PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<ApiResponse> getAllOrganizations() {
//
//		logger.debug("Extracting all the organization list..");
//
//		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),
//				MessageConstants.ORG_EXTRACTED, orgService.getAllOrganization()));
//	}

	// Request to get organization by OrgId (primary key)

	@GetMapping("/org/{orgId}")
	public ResponseEntity<ApiResponse> geOrganizationById(@Valid @PathVariable("orgId") Long orgId) {

		org.slf4j.Marker marker = null;
		logger.debug(marker, "Path variable captured is id {}", orgId);

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),
				MessageConstants.ORG_EXTRACTED_ID, orgService.getOrganizatiobyId(orgId)));

	}

	// Validate Org Reference Id
	@PostMapping("/org/validate")
	public ResponseEntity<ApiResponse> validateOrganization(@Valid @RequestParam("orgRefName") String orgRefName) {

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),
				MessageConstants.ORG_ID_AVAILABLE, orgService.checkOrgRefName(orgRefName)));
	}

	// Create Organization
	@PostMapping("/org")
	public ResponseEntity<ApiResponse> createOrganization(@Valid @RequestBody OrganizationRequest orgRequest) {

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(),
				MessageConstants.ORG_CREATED_SUCCESS, orgService.createOrganization(orgRequest)));
	}

	// Add Address for the Organization
	@PostMapping("/org/address")
	public ResponseEntity<ApiResponse> addAddressOfOrganization(
			@Valid @RequestBody OrganizationAddressRequest request) {

		Address address = new Address();
		try {
			// Validate Address Request
			address.setAddressLine1(request.getAddressLine1());
			address.setAddressLine2(request.getAddressLine2());
			address.setCity(request.getCity());
			address.setState(request.getState());
			address.setStreet(request.getStreet());
			address.setZipCode(request.getZipCode());
			address.setCountry(request.getCountry());
		} catch (Exception ep) {
			logger.error(MessageConstants.ERROR_IN_MAPPING + ep.getLocalizedMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(),
				MessageConstants.ADDRESS_ADDED, orgService.addAddress(request.getOrgId(), address)));
	}

	// Update Address

	@PutMapping("/org/address")
	public ResponseEntity<ApiResponse> updateAddress(@Valid @RequestBody AddressRequest request) {

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(),
				MessageConstants.ADDRESS_UPDATED, orgService.updateAddress(request)));
	}

	// Update Organization
	@PutMapping("/org")
	public ResponseEntity<ApiResponse> updateOrganization(@Valid @RequestBody Organization org) {

		return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(HttpStatus.CREATED.value(),
				MessageConstants.ORG_UPDATED, orgService.updateOrganization(org)));
	}

	
	// Returns all the organizations present in database.
	@GetMapping("/org")
	public ResponseEntity<ApiResponse> getAll() {

		logger.debug("Extracting all the organization list..");

		return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(HttpStatus.OK.value(),
				MessageConstants.ORG_EXTRACTED, orgService.testMethod()));
	}
}
