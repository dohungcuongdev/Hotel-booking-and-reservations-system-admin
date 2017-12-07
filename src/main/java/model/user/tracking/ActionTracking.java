package model.user.tracking;

import java.util.List;

public class ActionTracking {

	private List roomBooked;
	private List roomCanceled;
	private List feedbackroom;
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

	public ActionTracking(List roomBooked, List roomCanceled, List feedbackroom, double avgfeedbackRoom, double avgFeedbackSV) {
		super();
		this.roomBooked = roomBooked;
		this.roomCanceled = roomCanceled;
		this.feedbackroom = feedbackroom;
		this.avgfeedbackRoom = avgfeedbackRoom;
		this.avgFeedbackSV = avgFeedbackSV;
	}

	@Override
	public String toString() {
		return "ActionTracking [roomBooked=" + roomBooked + ", roomCanceled=" + roomCanceled + ", feedbackroom="
				+ feedbackroom + ", avgfeedbackRoom=" + avgfeedbackRoom + ", avgFeedbackSV=" + avgFeedbackSV + "]";
	}
}
