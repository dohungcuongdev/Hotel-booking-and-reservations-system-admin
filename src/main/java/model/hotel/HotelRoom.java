/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.hotel;

import java.util.Date;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import statics.AppData;
import statics.provider.DateTimeCalculator;

/**
 *
 * @author Do Hung Cuong
 */
public class HotelRoom extends HotelItem {

	private String size;
	private String numpeople;
	private String status;
	private String amenities;
	private String booked_by;
	private String avgAminities;
	private String checkin;
	private String checkout;
	private int star;
	private int numvote;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getNumpeople() {
		return numpeople;
	}

	public void setNumpeople(String numpeople) {
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

	public String getAvgAminities() {
		return avgAminities;
	}

	public void setAvgAminities(String avgAminities) {
		this.avgAminities = avgAminities;
	}

	@Override
	public void initializeSomeInfor() {
		if (status.equals("available")) {
			this.booked_by = "";
			this.checkin = "";
			this.checkout = "";
		}
	}

	@Override
	public void setNewInfor() {
		this.img = AppData.ROOM_DEFAULT_IMG[0];
		this.img2 = AppData.ROOM_DEFAULT_IMG[1];
		this.created_by = AppData.admin.getUsername();
		this.created_at = new Date().toString();
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
		Date checkindate = DateTimeCalculator.formatDateTime(checkin);
		Date checkoutdate = DateTimeCalculator.formatDateTime(checkout);
		return today.compareTo(checkindate) <= 0 && checkindate.compareTo(checkoutdate) <= 0;
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

	@Override
	public String toString() {
		return "HotelRoom{" + "id=" + id + ", name=" + name + ", size=" + size + ", numpeople=" + numpeople + ", status=" + status + ", amenities=" + amenities + ", booked_by=" + booked_by + ", avgAminities=" + avgAminities + ", checkin=" + checkin + ", checkout=" + checkout + ", star=" + star + ", numvote=" + numvote + '}';
	}

	@Override
	public DBObject toDBObject() {
		return BasicDBObjectBuilder.start("name", name).append("type", type).append("size", size).append("price", price).append("numpeople", numpeople).append("status", status).append("type", type).append("img", img).append("img2", img2).append("details", details).append("amenities", amenities).append("avgAminities", avgAminities).append("created_by", created_by).append("created_at", created_at).get();
	}
}
