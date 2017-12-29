package statics.provider;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import model.api.user.tracking.GeoLocation;

import java.io.IOException;

public class GeoLookup {

    private static LookupService lookUp;

    static {
        try {
            lookUp = new LookupService(GeoLookup.class.getResource("/GeoLiteCity.dat").getFile(), LookupService.GEOIP_MEMORY_CACHE);
        } catch (IOException e) {
            System.out.println("Could not load geo ip database: " + e.getMessage());
        }
    }

    public static GeoLocation getLocation(String externalIP) {
    	Location loc = lookUp.getLocation(externalIP);
        return new GeoLocation(loc.countryCode, loc.countryName, loc.postalCode, loc.city, loc.region,
                loc.area_code, loc.dma_code, loc.metro_code, loc.latitude, loc.longitude);
    }
}