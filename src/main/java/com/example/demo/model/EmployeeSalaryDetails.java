package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "empstaicslarydtls")
public class EmployeeSalaryDetails implements Serializable{

	private static final long serialVersionUID = -5871376853924311696L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Double id;
	private String uanNo;
	private String pfNo;
	private String esiNo;
	private String panNo;
	private String epsNo;
	private String grade;

	public EmployeeSalaryDetails() {
		super();
	}

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getUanNo() {
		return uanNo;
	}

	public void setUanNo(String uanNo) {
		this.uanNo = uanNo;
	}

	public String getPfNo() {
		return pfNo;
	}

	public void setPfNo(String pfNo) {
		this.pfNo = pfNo;
	}

	public String getEsiNo() {
		return esiNo;
	}

	public void setEsiNo(String esiNo) {
		this.esiNo = esiNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getEpsNo() {
		return epsNo;
	}

	public void setEpsNo(String epsNo) {
		this.epsNo = epsNo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "EmployeeSalaryDetails [id=" + id + ", uanNo=" + uanNo + ", pfNo=" + pfNo + ", esiNo=" + esiNo
				+ ", panNo=" + panNo + ", epsNo=" + epsNo + ", grade=" + grade + "]";
	}

}
