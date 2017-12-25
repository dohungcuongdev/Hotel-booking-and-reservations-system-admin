package model.api.user.tracking;

import java.util.List;

public class ActionTracking {

	private List<DataCollection> roomBooked;
	private List<DataCollection> roomCanceled;
	private List<Feedback> feedbackroom;
	private List<Feedback> feedbackservice;
	private double avgfeedbackRoom;
	private double avgFeedbackSV;

	public List<DataCollection> getRoomBooked() {
		return roomBooked;
	}

	public void setRoomBooked(List<DataCollection> roomBooked) {
		this.roomBooked = roomBooked;
	}

	public List<DataCollection> getRoomCanceled() {
		return roomCanceled;
	}

	public void setRoomCanceled(List<DataCollection> roomCanceled) {
		this.roomCanceled = roomCanceled;
	}

	public List<Feedback> getFeedbackroom() {
		return feedbackroom;
	}

	public void setFeedbackroom(List<Feedback> feedbackroom) {
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

	public List<Feedback> getFeedbackservice() {
		return feedbackservice;
	}

	public void setFeedbackservice(List<Feedback> feedbackservice) {
		this.feedbackservice = feedbackservice;
	}

	public ActionTracking(List<DataCollection> roomBooked, List<DataCollection> roomCanceled,
			List<Feedback> feedbackroom, List<Feedback> feedbackservice, double avgfeedbackRoom, double avgFeedbackSV) {
		super();
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
