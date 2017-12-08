package model.user.tracking;

import java.util.List;

public class ActionTracking {

	private List roomBooked;
	private List roomCanceled;
	private List feedbackroom;
	private List feedbackservice;
	private double avgfeedbackRoom;
	private double avgFeedbackSV;

	public List getRoomBooked() {
		return roomBooked;
	}

	public void setRoomBooked(List roomBooked) {
		this.roomBooked = roomBooked;
	}

	public List getRoomCanceled() {
		return roomCanceled;
	}

	public void setRoomCanceled(List roomCanceled) {
		this.roomCanceled = roomCanceled;
	}

	public List getFeedbackroom() {
		return feedbackroom;
	}

	public void setFeedbackroom(List feedbackroom) {
		this.feedbackroom = feedbackroom;
	}

	public double getAvgfeedbackRoom() {
		return avgfeedbackRoom;
	}

	public void setAvgfeedbackRoom(double avgfeedbackRoom) {
		this.avgfeedbackRoom = avgfeedbackRoom;
	}

	public double getAvgFeedbackSV() {
		return avgFeedbackSV;
	}

	public void setAvgFeedbackSV(double avgFeedbackSV) {
		this.avgFeedbackSV = avgFeedbackSV;
	}

	public List getFeedbackservice() {
		return feedbackservice;
	}

	public void setFeedbackservice(List feedbackservice) {
		this.feedbackservice = feedbackservice;
	}

	public ActionTracking(List roomBooked, List roomCanceled, List feedbackroom, List feedbackservice, double avgfeedbackRoom, double avgFeedbackSV) {
		this.roomBooked = roomBooked;
		this.roomCanceled = roomCanceled;
		this.feedbackroom = feedbackroom;
		this.feedbackservice = feedbackservice;
		this.avgfeedbackRoom = avgfeedbackRoom;
		this.avgFeedbackSV = avgFeedbackSV;
	}

	@Override
	public String toString() {
		return "ActionTracking [roomBooked=" + roomBooked + ", roomCanceled=" + roomCanceled + ", feedbackroom="
				+ feedbackroom + ", feedbackservice=" + feedbackservice + ", avgfeedbackRoom=" + avgfeedbackRoom
				+ ", avgFeedbackSV=" + avgFeedbackSV + "]";
	}
}
