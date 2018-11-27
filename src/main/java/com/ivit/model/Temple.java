package com.ivit.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.annotations.QueryEntity;
@QueryEntity
@Document
public class Temple implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2750723563373490960L;
	@Id
	public ObjectId _id;
	private String name;
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
