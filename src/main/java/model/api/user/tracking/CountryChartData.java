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
public class CountryChartData {

	private String countryCode;
	private String countryName;
	private int visitTime;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public int getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(int visitTime) {
		this.visitTime = visitTime;
	}

	@Override
	public String toString() {
		return "GeoSameCountry [countryCode=" + countryCode + ", countryName=" + countryName + ", visitTime="
				+ visitTime + "]";
	}
}
