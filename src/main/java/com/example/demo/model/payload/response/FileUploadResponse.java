package com.example.demo.model.payload.response;

public class FileUploadResponse {

	private String fileName;
	private int successCount;
	private int failureCount;
	private int totalCount;
	private String message;

	public FileUploadResponse() {
		super();
	}

	public FileUploadResponse(String fileName, int successCount, int failureCount, int totalCount, String message) {
		super();
		this.fileName = fileName;
		this.successCount = successCount;
		this.failureCount = failureCount;
		this.totalCount = totalCount;
		this.message = message;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}

	public int getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(int failureCount) {
		this.failureCount = failureCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "FileUploadResponse [fileName=" + fileName + ", successCount=" + successCount + ", failureCount="
				+ failureCount + ", totalCount=" + totalCount + ", message=" + message + "]";
	}

}
