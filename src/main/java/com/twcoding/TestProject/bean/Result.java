package com.twcoding.TestProject.bean;

import java.io.Serializable;

import com.google.gson.Gson;

public class Result implements Serializable{
	private static final long serialVersionUID = 1237763153607560570L;
	
	private ResultCode error;

	public ResultCode getError() {
		return error;
	}

	public void setError(ResultCode error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
}
