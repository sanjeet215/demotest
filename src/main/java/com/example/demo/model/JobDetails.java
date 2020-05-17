package com.example.demo.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.configuration.Auditable;

@Entity
@Table(name = "jobdetails")
public class JobDetails extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 8569244746594301956L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Double jobId;
	private String jobType;
	private String jobTitle;
	private String employmentType;
	private LocalDate hireDate;
	private LocalDate lastDate;

	public JobDetails() {
		super();
	}

	public Double getJobId() {
		return jobId;
	}

	public void setJobId(Double jobId) {
		this.jobId = jobId;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(String employmentType) {
		this.employmentType = employmentType;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public LocalDate getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}

	@Override
	public String toString() {
		return "JobDetails [jobId=" + jobId + ", jobType=" + jobType + ", jobTitle=" + jobTitle + ", employmentType="
				+ employmentType + ", hireDate=" + hireDate + ", lastDate=" + lastDate + "]";
	}

}
