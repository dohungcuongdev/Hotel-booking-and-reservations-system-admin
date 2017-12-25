/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.mysql.hotel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import statics.AppData;
import statics.provider.DateTimeCalculator;

/**
 *
 * @author Do Hung Cuong
 */

@Entity(name = "rooms")
public class HotelRoom extends HotelItem {
	
	@Column(name = "size")
	private int size;
	
	@Column(name = "numpeople")
	private int numpeople;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "amenities")
	private String amenities;
	
	@Column(name = "booked_by")
	private String booked_by;
	
	@Column(name = "avgAminities")
	private int avgAminities;
	
	@Column(name = "checkin")
	private Date checkin;
	
	@Column(name = "checkout")
	private Date checkout;
	
	@Column(name = "star")
	private int star;
	
	@Column(name = "numvote")
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

	public Date getCheckin() {
		return checkin;
	}

	public void setCheckin(Date checkin) {
		this.checkin = checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public void setCheckout(Date checkout) {
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
		Date today = DateTimeCalculator.getToday();
		return today.compareTo(checkin) <= 0 && checkin.compareTo(checkout) <= 0;
	}

	public boolean isCorrectRoomName() {
		return (name.length() == 3 && checkNaturalNumber(name));
	}

	@Override
	public String getAbleToUpdate() {
		return !isEnoughInfor() ? AppData.INFOR_NOT_ENOUGH : !isCorrectRoomName() ? AppData.WRONG_ROOM_NAME : (!isNumberFormat())  ? AppData.WRONG_NUMBER_FORMAT_ROOM : (isInvalidType())  ? AppData.WRONG_TYPE_ROOM : (isInvalidStatus())  ? AppData.WRONG_STATUS_ROOM : (!isvalidDate())  ? AppData.WRONG_CHECKIN_CHECKOUT: AppData.ABLE_TO_EDIT;
	}

	public boolean allInforCorrect() {
		return isEnoughInfor() && !isInvalidType() && !isInvalidStatus() && isvalidDate() && isNumberFormat();
	}
	
	public HotelRoom() {}
	
	public HotelRoom(String name, int price, String img, String img2, String details, String type, String created_by, Date created_at, int size, int numpeople, String status, String amenities, String booked_by, int avgAminities, Date checkin, Date checkout, int star, int numvote) {
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

	public HotelRoom(int id, String name, int price, String img, String img2, String details, String type, String created_by, Date created_at, int size, int numpeople, String status, String amenities, String booked_by, int avgAminities, Date checkin, Date checkout, int star, int numvote) {
		this(name, price, img, img2, details, type, created_by, created_at, size, numpeople, status, amenities, booked_by, avgAminities, checkin, checkout, star, numvote);
		this.id = id;
	}

	@Override
	public String toString() {
		return "HotelRoom [ id=" + id + ", size=" + size + ", numpeople=" + numpeople + ", status=" + status + ", amenities=" + amenities + ", booked_by=" + booked_by + ", avgAminities=" + avgAminities + ", checkin=" + checkin + ", checkout=" + checkout + ", star=" + star + ", numvote=" + numvote + ", name=" + name + ", price=" + price + ", img=" + img + ", img2=" + img2 + ", details=" + details + ", type=" + type + ", created_by=" + created_by + ", created_at=" + created_at + "]";
	}
	
}
