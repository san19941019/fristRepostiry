package com.base.bo;

public class ResultBo<T> {
 private Integer code=200;
 private String msg;
 private T data;
 
public ResultBo() {
	
}
public ResultBo(Integer code, String msg) {
	this.msg = msg;
	this.code = code;
}
public ResultBo(String msg) {
	super();
	this.msg = msg;
}


public Integer getCode() {
	return code;
}
public ResultBo setCode(Integer code) {
	this.code = code;
	return this;
}
public String getMsg() {
	return msg;
}
public ResultBo setMsg(String msg) {
	this.msg = msg;
	return this;
}
public T getData() {
	return data;
}
public ResultBo setData(T data) {
	this.data = data;
	return this;
}
	
}
