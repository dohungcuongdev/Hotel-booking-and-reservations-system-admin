package test;

import model.hotel.HotelRoom;

public class SimpleGeneric<T> {
	
    final Class<T> typeParameterClass;

    public SimpleGeneric(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
	
	
	public static void main(String args[]) {
		SimpleGeneric<HotelRoom> r = new SimpleGeneric<HotelRoom>(null);
		System.out.println();
		
	}
	

}
