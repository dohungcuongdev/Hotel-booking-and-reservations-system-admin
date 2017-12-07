package test;

import java.util.Arrays;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {
		String test="Shower.Bathtub.One bed for 2 adult.Seating unit.Telephone.Mini-fridge.Hair dryer.TV 39 inchs.Air-conditioner.Free WiFi";
		
		List list = Arrays.asList(test.split("\\."));
		System.out.println(list);
	}

}
