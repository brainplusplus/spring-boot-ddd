package com.app.object;

public class ResponseListObject<T> extends ResponseObject<T> {
	public long draw;
	public long recordsTotal;
	public long recordsFiltered;
	public long total;
	public Iterable<T> rows;
}
