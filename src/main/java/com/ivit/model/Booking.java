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

	private String email;
	private String objective;
	private String templeId;
	private String lat;
	private String lng;
	private Integer quantity;
	private String tel;
	private String payment;
	private String status;
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

	public String getTempleId() {
		return templeId;
	}

	public void setTempleId(String templeId) {
		this.templeId = templeId;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



}
