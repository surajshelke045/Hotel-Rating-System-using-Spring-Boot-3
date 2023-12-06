package com.hotel.Service;

import java.util.List;

import com.hotel.Entities.Hotel;

public interface HotelService {
	
	Hotel createHotel(Hotel hotel);
	
	Hotel updateHotel(Hotel hotel,String id);
	
	Hotel getHotel(String id);
	
	void deleteHotel(String id);
	
	List<Hotel> getAllhotels();
	
	

}
