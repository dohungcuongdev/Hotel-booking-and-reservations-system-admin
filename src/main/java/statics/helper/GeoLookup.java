package statics.helper;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;

import model.api.user.tracking.GeoLocation;

public class GeoLookup {

    private static LookupService lookUp;

    static {
        try {
        	Resource resource = new ClassPathResource("/GeoLiteCity.dat");
            lookUp = new LookupService(resource.getFile(), LookupService.GEOIP_MEMORY_CACHE);
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