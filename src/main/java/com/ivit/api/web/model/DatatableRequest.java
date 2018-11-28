package com.ivit.api.web.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class DatatableRequest<T> implements Serializable {
	private Integer start;
	private Integer length;
	private Integer draw;
	
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLength() {
		return length;
	}
	public void setLength(Integer length) {
		this.length = length;
	}

}
