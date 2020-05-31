package com.base.bo;

public class PageQuery<T> {
	private Integer page;
	private Integer size;
	private T query;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public T getQuery() {
		return query;
	}
	public void setQuery(T query) {
		this.query = query;
	}
	
}
