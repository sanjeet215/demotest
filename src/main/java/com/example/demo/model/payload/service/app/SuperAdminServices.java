package com.example.demo.model.payload.service.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Organization;
import com.example.demo.model.payload.request.OrganizationRequest;
import com.example.demo.model.payload.response.FileUploadResponse;
import com.example.demo.model.payload.service.AuthService;
import com.example.demo.model.payload.service.DataUploadService;
import com.example.demo.model.payload.service.EmplyoeeService;
import com.example.demo.model.payload.service.OrganizationService;
import com.example.demo.utility.ReadExcelSheetService;

@Service
public class SuperAdminServices {

	private static final Logger logger = LoggerFactory.getLogger(SuperAdminServices.class);

	@Autowired
	AuthService authService;

	@Autowired
	OrganizationService orgService;

	@Autowired
	EmplyoeeService empService;

	@Autowired
	ReadExcelSheetService readExcel;

	@Autowired
	DataUploadService datauploadService;

	/* Onboard 1 organization */
	@Transactional
	public void onBoardOrganization(OrganizationRequest orgRequest) {

		/* 1. Make an entry in organiation table */
		Organization org = orgService.createOrganization(orgRequest);

		/* 2. Make an entry in Employee table */
		empService.createEmployee(orgRequest, org);

		/* 3. Register user to allow login to application */
		authService.registerUser(orgRequest);

	}

	/* On-board Multiple organization */
	public FileUploadResponse onBoardOrganizationList(String fileName) {

		int successCounter = 0;
		int failureCounter = 0;

		FileUploadResponse response = new FileUploadResponse();
		response.setFileName(fileName);

		List<String> fileContent = readExcel.readLeaveTypeExcel(fileName);
		response.setTotalCount(Math.toIntExact(fileContent.stream().skip(1).count()));

		List<OrganizationRequest> requestList = new ArrayList<>();
		fileContent.stream().skip(1).distinct().map(item -> item.split(";")).filter(row -> row.length == 7)
				.collect(Collectors.toList()).forEach(item -> {

					OrganizationRequest request = new OrganizationRequest();
					request.setOrgRefName(item[0]);
					request.setOrgName(item[1]);
					request.setDescription(item[2]);
					request.setFirstName(item[3]);
					request.setLastName(item[4]);
					request.setContactNumber(item[5]);
					request.setContactEmail(item[6]);

					requestList.add(request);
				});

		for (OrganizationRequest request : requestList) {

			if (datauploadService.uploadOrganization(request)) {
				successCounter = successCounter + 1;
			} else {
				failureCounter = failureCounter + 1;
			}
		}

		response.setSuccessCount(successCounter);
		response.setFailureCount(failureCounter);

		if (failureCounter >= 1) {
			response.setMessage(successCounter + " records uploaded successfullt and " + failureCounter
					+ " failed. Please refer my error section for reference");
		} else {
			response.setMessage(successCounter + " records uploaded successfully");
		}

		logger.debug("Final response --> {}", response);

		return response;
	}

}
