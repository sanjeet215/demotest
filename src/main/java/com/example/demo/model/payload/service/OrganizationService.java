package com.example.demo.model.payload.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.constants.MessageConstants;
import com.example.demo.exception.ResourceAlreadyExistException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.SomeInternalExceptionOccured;
import com.example.demo.model.Address;
import com.example.demo.model.Organization;
import com.example.demo.model.payload.request.AddressRequest;
import com.example.demo.model.payload.request.OrganizationRequest;
import com.example.demo.model.payload.response.OrganizationResponse;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.OrganizationRepository;

@Service
public class OrganizationService {

	private static final Logger logger = LoggerFactory.getLogger(OrganizationService.class);

	@Autowired
	OrganizationRepository orgRepo;

	@Autowired
	AddressRepository addressRepo;

	// Add New Organization

	@Transactional
	public Organization createOrganization(OrganizationRequest orgRequest) {

		if (orgRepo.existsByorgRefName(orgRequest.getOrgRefName())) {
			logger.debug("Organization Reference Name is already taken.");
			throw new ResourceAlreadyExistException(MessageConstants.ORG_ID_IN_USE);
		}

		Organization organization = new Organization();

		try {
			organization.setOrgName(orgRequest.getOrgName());
			organization.setOrgRefName(orgRequest.getOrgRefName());
			organization.setDescription(orgRequest.getDescription());
			organization.setStatus(true);
		} catch (Exception ep) {
			logger.error("Error occured while  assigning the object");
			throw new SomeInternalExceptionOccured(ep.getLocalizedMessage());
		}
		logger.debug("Setting organization status to true while creating the organization");

		return orgRepo.save(organization);
	}

	/*
	 * Check if Organization Referencename is already there. This must a unique
	 * field.
	 */
	public boolean checkOrgRefName(String orgRefname) {
		return !orgRepo.existsByorgRefName(orgRefname);
	}

	// Update New Organization. Organization Reference Name and ID can't be updated.
	// Also status can't be updated in this request

	public Organization updateOrganization(Organization orgUpdated) {

		// validate user access for the record

		logger.debug("update organization request");

		return orgRepo.findById(orgUpdated.getOrgId()).map(company -> {
			company.setOrgName(orgUpdated.getOrgName());
			company.setDescription(orgUpdated.getDescription());
			return orgRepo.save(company);

		}).orElseThrow(() -> new ResourceNotFoundException("Orgnization Id : " + orgUpdated.getOrgId() + "not found"));
	}

	// Activate/Deactivate Organization

	public Organization activateOrDeactivate(Long orgId, boolean status) {

		org.slf4j.Marker marker = null;

		logger.debug(marker, "Orgaziname id {}", orgId);

		return orgRepo.findByorgId(orgId).map(company -> {
			company.setStatus(status);
			return orgRepo.save(company);
		}).orElseThrow(() -> new ResourceNotFoundException("Orgnization Id : " + orgId + "not found"));
	}

	// Upload organization image

	// Get all organization
	public List<Organization> getAllOrganization() {

		List<Organization> organizationList = orgRepo.findAll();

		if (!organizationList.isEmpty()) {
			return organizationList;
		} else {
			throw new ResourceNotFoundException("No organizations are registered yet");
		}

	}

	// Get organization by Id

	public Organization getOrganizatiobyId(Long orgId) {

		Optional<Organization> org = orgRepo.findByorgId(orgId);

		if (!org.isPresent()) {
			throw new ResourceNotFoundException("Please check the request. OrgID not found");
		} else {
			return org.get();
		}

	}

	// Add Orgnization address
	public Organization addAddress(Long orgId, Address address) {

		Optional<Organization> org = orgRepo.findByorgId(orgId);

		if (!org.isPresent()) {
			throw new ResourceNotFoundException("Please check the request. OrgID not found");
		} else {

			Organization organization = org.get();

			if (organization.getAddress() != null) {
				throw new ResourceAlreadyExistException("Adderss already exist, Please try to update");
			} else {
				organization.setAddress(address);
				return orgRepo.save(organization);
			}
		}
	}

	// Update organization address

	public Address updateAddress(AddressRequest request) {

		Address address = addressRepo.findByaddressId(request.getAddressId())
				.orElseThrow(() -> new ResourceNotFoundException("Address Id not found to update"));

		try {

			address.setAddressLine1(request.getAddressLine1());
			address.setAddressLine2(request.getAddressLine2());
			address.setCity(request.getCity());
			address.setStreet(request.getStreet());
			address.setState(request.getState());
			address.setZipCode(request.getZipCode());

		} catch (Exception ep) {
			throw new SomeInternalExceptionOccured("Error while processing the request" + ep.getLocalizedMessage());
		}

		return addressRepo.save(address);

	}

	// Test Method for LazyLoading
	public List<OrganizationResponse> getallOrganization() {

		List<Organization> orgList = orgRepo.findAll();
		List<OrganizationResponse> responseList = new ArrayList<>();

		orgList.forEach(item -> {

			OrganizationResponse response = new OrganizationResponse();

			response.setOrgId(item.getOrgId());
			response.setOrgRefName(item.getOrgRefName());
			response.setDescription(item.getDescription());
			response.setStatus(item.isStatus());
			response.setOrgName(item.getOrgName());
			response.setImaeUrl(item.getImageUrl());
			
			responseList.add(response);
		});

		return responseList;
	}
}
