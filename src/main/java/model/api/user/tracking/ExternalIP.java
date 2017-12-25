/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.api.user.tracking;

import java.util.List;

/**
 *
 * @author HUNGCUONG
 */
public class ExternalIP {

	private String external_ip_address;
	private List<Integer> range;
	private String country;
	private String region;
	private String city;
	private List<Double> ll;
	private int metro;
	private int zip;

	public String getExternal_ip_address() {
		return external_ip_address;
	}

	public void setExternal_ip_address(String external_ip_address) {
		this.external_ip_address = external_ip_address;
	}

	public List<Integer> getRange() {
		return range;
	}

	public void setRange(List<Integer> range) {
		this.range = range;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Double> getLl() {
		return ll;
	}

	public void setLl(List<Double> ll) {
		this.ll = ll;
	}

	public int getMetro() {
		return metro;
	}

	public void setMetro(int metro) {
		this.metro = metro;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "ExternalIP [external_ip_address=" + external_ip_address + ", range=" + range + ", country=" + country
				+ ", region=" + region + ", city=" + city + ", ll=" + ll + ", metro=" + metro + ", zip=" + zip + "]";
	}
}
