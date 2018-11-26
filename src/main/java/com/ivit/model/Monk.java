package com.ivit.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.annotations.QueryEntity;

@QueryEntity
@Document
public class Monk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7153502589161341899L;
	@Id
	public ObjectId _id;
	private String name;
	private String nickname;
	private String surname;
	private Integer age;
	private Integer year;
	private String level;
	private String comment;



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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Monk(String name, String nickname, String surname, Integer age, Integer year, String level, String comment) {
		super();
		this.name = name;
		this.nickname = nickname;
		this.surname = surname;
		this.age = age;
		this.year = year;
		this.level = level;
		this.comment = comment;
	}

}
