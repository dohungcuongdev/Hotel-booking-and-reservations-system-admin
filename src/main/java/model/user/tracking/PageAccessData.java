package model.user.tracking;

public class PageAccessData {
	private String page_access;
	private int visit_time;
	private String color;

	public String getPage_access() {
		return page_access;
	}

	public void setPage_access(String page_access) {
		this.page_access = page_access;
	}

	public int getVisit_time() {
		return visit_time;
	}

	public void setVisit_time(int visit_time) {
		this.visit_time = visit_time;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public PageAccessData(String page_access, int visit_time) {
		this.page_access = page_access;
		this.visit_time = visit_time;
		this.color = color;
	}

	@Override
	public String toString() {
		return "PageAccessData [page_access=" + page_access + ", visit_time=" + visit_time + ", color=" + color + "]";
	}
}
