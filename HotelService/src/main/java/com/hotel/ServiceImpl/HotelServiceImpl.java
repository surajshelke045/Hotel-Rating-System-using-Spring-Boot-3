package com.hotel.ServiceImpl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.Entities.Hotel;
import com.hotel.Exception.ResourceNotFoundException;
import com.hotel.Repository.HotelRepository;
import com.hotel.Service.HotelService;

@Service
public class HotelServiceImpl  implements HotelService{

	@Autowired
	private HotelRepository hotelRepository;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		String randomUserId = UUID.randomUUID().toString();
		hotel.setId(randomUserId);
		return hotelRepository.save(hotel);
	}

	@Override
	public Hotel updateHotel(Hotel hotel, String id) {
	     Hotel hotel1 = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given ID id not Found !!"));
	     hotel1.setName(hotel.getName());
	     hotel1.setLocation(hotel.getLocation());
	     hotel1.setAbout(hotel.getAbout());
		return hotelRepository.save(hotel1);
	}

	@Override
	public Hotel getHotel(String id) {
		Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given ID id not Found !!"));
		return hotel;
	}

	@Override
	public void deleteHotel(String id) {
		Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Hotel with given ID id not Found !!"));
	    hotelRepository.delete(hotel);
	}

	@Override
	public List<Hotel> getAllhotels() {
		return hotelRepository.findAll();
	}

}
