package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Blob;

import com.example.demo.configuration.Auditable;

@Entity
@Table(name = "errors")
public class CustomErrors extends Auditable<String> implements Serializable {

	private static final long serialVersionUID = 3086148813809522157L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long errorid;

	private String message;

	private String record;

	private String exceptionMessage;

	public CustomErrors() {
		super();
	}

	public CustomErrors(String message, String record, String exceptionMessage) {
		super();
		this.message = message;
		this.record = record;
		this.exceptionMessage = exceptionMessage;
	}

	public Long getErrorid() {
		return errorid;
	}

	public void setErrorid(Long errorid) {
		this.errorid = errorid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	@Override
	public String toString() {
		return "CustomErrors [errorid=" + errorid + ", message=" + message + ", record=" + record
				+ ", exceptionMessage=" + exceptionMessage + "]";
	}

}
