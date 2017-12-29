package testGeoIP;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class LookUpProgram {

	public static void testGEO(String exIP) {
		long ipAddress;
		try {
			ipAddress = new BigInteger(InetAddress.getByName(exIP).getAddress()).longValue();

			System.out.println("By String IP address: \n" + GeoIPv4.getLocation(exIP));

			System.out.println("By long IP address: \n" + GeoIPv4.getLocation(ipAddress));

			System.out.println(
					"By InetAddress IP address: \n" + GeoIPv4.getLocation(InetAddress.getByName(exIP)));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String... args) throws UnknownHostException {
		testGEO("72.229.28.185");
	}
}