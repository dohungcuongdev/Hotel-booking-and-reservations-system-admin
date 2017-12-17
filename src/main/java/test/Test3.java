package test;

import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class Test3 {

/**
 *
 * @author Do Hung Cuong
 * @throws IOException 
 * @throws ClientProtocolException 
 */
	public static void main(String[] args) throws ClientProtocolException, IOException {
		HttpPut httpPut = new HttpPut("http://localhost:8080/Hotel-booking-and-reservations-system-admin/api/rooms/808");
		httpPut.addHeader("Content-Type", "application/json");
		httpPut.addHeader("Content-Length", "LENGTH");
		httpPut.addHeader("Key", "ad412f36a2eecbcd5c0e323e");
		System.out.println("put");
//		httpPut.setEntity((HttpEntity) keyArg);
//        HttpResponse response = httpClient.execute(httpPut);
	    
	}
}
