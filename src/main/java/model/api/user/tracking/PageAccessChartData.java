package model.api.user.tracking;

public class PageAccessChartData {
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
	
	private String getColorByVisitTime() {
		String colors[] = { "#000000", "#333333", "#999999", "#DDDDDD", "#754DEB", "#CD0D74", "#8A0CCF", "#2A0CD0", "#0D52D1", "#0D8ECF", "#04D215", "#B0DE09", "#F8FF01", "#FCD202", "#FF9E01", "#FF6600", "#FF0F00" };
		int colorOrders[] = {10, 20, 30, 40, 50, 100, 125, 150, 200, 250, 300, 400, 500, 580, 600, 675};		
		for(int i = 0; i < colorOrders.length; i++)
			if(visit_time < colorOrders[i]) 
				return colors[i];
		return colors[16];
	}

	public PageAccessChartData(String page_access, int visit_time) {
		this.page_access = page_access;
		this.visit_time = visit_time;
		this.color = getColorByVisitTime();
	}

	@Override
	public String toString() {
		return "PageAccessData [page_access=" + page_access + ", visit_time=" + visit_time + ", color=" + color + "]";
	}
}
