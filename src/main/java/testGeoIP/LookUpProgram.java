package testGeoIP;

import java.net.UnknownHostException;

import org.json.JSONException;

import statics.provider.GeoLookup;

public class LookUpProgram {

	public static void testGEO(String exIP) {
		System.out.println("By String IP address: \n" + GeoLookup.getLocation(exIP));
	}

	public static void main(String... args) throws UnknownHostException, JSONException {
		testGEO("72.229.28.185");
	}
}