package com.sunqubit.faqture.beans.rest;

public class ApiRestFullResponse {
	private RestFullResponseHeader response;
	private Object data;
	
	public ApiRestFullResponse(RestFullResponseHeader response, Object data) {
		this.response = response;
		this.data = data;
	}
	public RestFullResponseHeader getResponse() {
		return response;
	}
	public void setResponse(RestFullResponseHeader response) {
		this.response = response;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
