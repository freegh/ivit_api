package com.ivit.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mysema.query.annotations.QueryEntity;

@QueryEntity
@Document
public class Booking implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2750723563373490960L;
	@Id
	public ObjectId _id;
	private ObjectId memberId;

	private LocalDateTime fromdate;
	private LocalDateTime todate;

	private String objective;
	private String placeId;
	private List<Monk> monks;

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public ObjectId getMemberId() {
		return memberId;
	}

	public void setMemberId(ObjectId memberId) {
		this.memberId = memberId;
	}

	public LocalDateTime getFromdate() {
		return fromdate;
	}

	public void setFromdate(LocalDateTime fromdate) {
		this.fromdate = fromdate;
	}

	public LocalDateTime getTodate() {
		return todate;
	}

	public void setTodate(LocalDateTime todate) {
		this.todate = todate;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public List<Monk> getMonks() {
		return monks;
	}

	public void setMonks(List<Monk> monks) {
		this.monks = monks;
	}

	public String getPlaceId() {
		return placeId;
	}

	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

}
