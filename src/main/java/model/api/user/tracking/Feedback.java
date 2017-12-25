/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.user.tracking;

/**
 *
 * @author HUNGCUONG
 */
public class Feedback {
    
    private String date;
    private String room;
    private int star;
    private String feedback;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Feedback(String date, int star, String feedback) {
		this.date = date;
		this.star = star;
		this.feedback = feedback;
	}

	public Feedback(String date, String room, int star, String feedback) {
        this.date = date;
        this.room = room;
        this.star = star;
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "FeedbackRoom{" + "date=" + date + ", room=" + room + ", star=" + star + ", feedback=" + feedback + '}';
    }
}
