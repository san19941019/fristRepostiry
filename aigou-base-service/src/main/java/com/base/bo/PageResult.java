package com.base.bo;

import java.util.List;

public class PageResult<T> {

	private Long total;
	
	private List<T> data;

	public PageResult(Long total, List<T> data) {
		super();
		this.total = total;
		this.data = data;
	}

	public PageResult( List<T> data) {
		super();
		this.data = data;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
}
