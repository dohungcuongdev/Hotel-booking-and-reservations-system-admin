package test;

import java.net.UnknownHostException;

import org.json.JSONException;

import statics.helper.GeoLookup;

public class TestGeoIPLookup {

	public static void testGEO(String exIP) {
		System.out.println("By String IP address: \n" + GeoLookup.getLocation(exIP));
	}

	public static void main(String... args) throws UnknownHostException, JSONException {
		testGEO("72.229.28.185");
	}
}