package com.qa.constants;

public enum HttpMethod {
	GET("get"), POST("post"), DELETE("delete"), PUT("put"), PATCH("patch");

	String method;

	private HttpMethod(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}
}
