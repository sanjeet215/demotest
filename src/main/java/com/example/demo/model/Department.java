package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.configuration.Auditable;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "department")
public class Department extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 291823838206037521L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long deptId;

	private String deptName;

	private String description;

	private boolean status;

	@JsonIgnore
	@OneToMany(mappedBy = "dept")
	private List<Employee> employees;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "org_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Organization organization;

	public Department() {
		super();
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", deptName=" + deptName + ", description=" + description + ", status="
				+ status + ", employees=" + employees + "]";
	}

}
