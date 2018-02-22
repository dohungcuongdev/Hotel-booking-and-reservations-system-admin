/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.sql.hotel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

import statics.constant.AppData;
import statics.helper.DateTimeCalculator;

/**
 *
 * @author Do Hung Cuong
 */

@Entity(name = "rooms")
public class HotelRoom extends HotelItem {
	
	@Column(name = "size", nullable = false)
	private int size;
	
	@Column(name = "numpeople", nullable = false)
	private int numpeople;
	
	@Column(name = "status", nullable = false)
	private String status;
	
	@Column(name = "booked_by", nullable = true)
	private String booked_by;
	
	@Column(name = "amenities", nullable = false)
	@Type(type="text")
	private String amenities;
	
	@Column(name = "avgAminities", nullable = false)
	private int avgAminities;
	
	@Column(name = "checkin", nullable = true)
	private String checkin;
	
	@Column(name = "checkout", nullable = true)
	private String checkout;
	
	@Column(name = "star", nullable = true)
	private int star;
	
	@Column(name = "numvote", nullable = true)
	private int numvote;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getNumpeople() {
		return numpeople;
	}

	public void setNumpeople(int numpeople) {
		this.numpeople = numpeople;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAmenities() {
		return amenities;
	}

	public void setAmenities(String amenities) {
		this.amenities = amenities;
	}

	public String getBooked_by() {
		return booked_by;
	}

	public void setBooked_by(String booked_by) {
		this.booked_by = booked_by;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}

	public int getNumvote() {
		return numvote;
	}

	public void setNumvote(int numvote) {
		this.numvote = numvote;
	}

	public int getAvgAminities() {
		return avgAminities;
	}

	public void setAvgAminities(int avgAminities) {
		this.avgAminities = avgAminities;
	}

	@Override
	public void initializeSomeInfor() {
		if (status.equals("available")) {
			this.booked_by = null;
			this.checkin = null;
			this.checkout = null;
		}
	}

	@Override
	public void setNewInfor() {
		this.img = AppData.ROOM_DEFAULT_IMG[0];
		this.img2 = AppData.ROOM_DEFAULT_IMG[1];
		super.setCreated();
		this.status = "available";
		initializeSomeInfor();
	}

	private boolean isInvalidType() {
		return !AppData.ROOM_TYPES.contains(type);
	}

	private boolean isInvalidStatus() {
		return !AppData.ROOM_STATUS.contains(status);
	}

	private boolean isEnoughInfor() {
		return checkNotNull(name, type, size, price, status, details, numpeople, amenities, avgAminities, created_by);
	}

	private boolean isNumberFormat() {
		return checkNaturalNumber(price, size, numpeople, avgAminities);
	}

	public boolean isvalidDate() {
		if (status.equals("available"))
			return true;
		else if(checkin!=null && checkout!=null && !checkin.equals("") && !checkout.equals("")) {
			Date today = DateTimeCalculator.getToday();
			Date checkindate = DateTimeCalculator.formatDateTime(checkin);
			Date checkoutdate = DateTimeCalculator.formatDateTime(checkout);
			return today.compareTo(checkindate) <= 0 && checkindate.compareTo(checkoutdate) <= 0;
		}
		return false;
	}

	public boolean isCorrectRoomName() {
		return (name.length() == 3 && checkNaturalNumber(name));
	}

	@Override
	public String isAbleToUpdate() {
		return !isEnoughInfor() ? AppData.INFOR_NOT_ENOUGH : !isCorrectRoomName() ? AppData.WRONG_ROOM_NAME : (!isNumberFormat())  ? AppData.WRONG_NUMBER_FORMAT_ROOM : (isInvalidType())  ? AppData.WRONG_TYPE_ROOM : (isInvalidStatus())  ? AppData.WRONG_STATUS_ROOM : (!isvalidDate())  ? AppData.WRONG_CHECKIN_CHECKOUT: AppData.ABLE_TO_EDIT;
	}

	public boolean allInforCorrect() {
		return isEnoughInfor() && !isInvalidType() && !isInvalidStatus() && isvalidDate() && isNumberFormat();
	}
	
	public boolean isReadyToBook() {
		return checkNotNull(booked_by, checkin, checkout) && isvalidDate();
	}
	
	public boolean isReadyToFeedback() {
		return checkNaturalNumber(star, numvote);
	}
	
	public HotelRoom() {}
	
	public HotelRoom(String name, int price, String img, String img2, String details, String type, String created_by, String created_at, int size, int numpeople, String status, String amenities, String booked_by, int avgAminities, String checkin, String checkout, int star, int numvote) {
		super(name, price, img, img2, details, type, created_by, created_at);
		this.size = size;
		this.numpeople = numpeople;
		this.status = status;
		this.amenities = amenities;
		this.booked_by = booked_by;
		this.avgAminities = avgAminities;
		this.checkin = checkin;
		this.checkout = checkout;
		this.star = star;
		this.numvote = numvote;
	}

	public HotelRoom(int id, String name, int price, String img, String img2, String details, String type, String created_by, String created_at, int size, int numpeople, String status, String amenities, String booked_by, int avgAminities, String checkin, String checkout, int star, int numvote) {
		this(name, price, img, img2, details, type, created_by, created_at, size, numpeople, status, amenities, booked_by, avgAminities, checkin, checkout, star, numvote);
		this.id = id;
	}

	@Override
	public String toString() {
		return "HotelRoom [ id=" + id + ", name=" + name + ", size=" + size + ", numpeople=" + numpeople + ", status=" + status + ", amenities=" + amenities + ", booked_by=" + booked_by + ", avgAminities=" + avgAminities + ", checkin=" + checkin + ", checkout=" + checkout + ", star=" + star + ", numvote=" + numvote + ", price=" + price + ", img=" + img + ", img2=" + img2 + ", details=" + details + ", type=" + type + ", created_by=" + created_by + ", created_at=" + created_at + "]";
	}	
	
	//use for old DAOs: mongodb DAOs
	@Override
	public DBObject toDBObject() {
		return BasicDBObjectBuilder.start("id", id).append("name", name).append("type", type).append("size", size).append("price", price).append("numpeople", numpeople).append("status", status).append("type", type).append("img", img).append("img2", img2).append("details", details).append("amenities", amenities).append("avgAminities", avgAminities).append("created_by", created_by).append("created_at", created_at).get();
	}
	
}