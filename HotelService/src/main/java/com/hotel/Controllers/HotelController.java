package com.hotel.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.Entities.Hotel;
import com.hotel.PayLoad.ApiResponse;
import com.hotel.Service.HotelService;

@RestController
@RequestMapping("/hotels")
public class HotelController {
	
	@Autowired
	private HotelService hotelService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/")
	public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
	{
		Hotel createHotel = hotelService.createHotel(hotel);
		return new ResponseEntity<Hotel>(createHotel,HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel,@PathVariable String id)
	{
		Hotel updateHotel = hotelService.updateHotel(hotel,id);
		return new ResponseEntity<Hotel>(updateHotel,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal')")
	@GetMapping("/{id}")
	public ResponseEntity<Hotel> getHotelById(@PathVariable String id)
	{
	 Hotel hotel = hotelService.getHotel(id);
	 return new ResponseEntity<Hotel>(hotel,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteHotel(@PathVariable String id)
	{
	  hotelService.deleteHotel(id);
	 return new ResponseEntity<ApiResponse>(new ApiResponse("Hotel Deleted Successfully !!",true,HttpStatus.OK),HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping
	public ResponseEntity<List<Hotel>> getAllhotels()
	{
		List<Hotel> allhotels = hotelService.getAllhotels();
		return new ResponseEntity<List<Hotel>>(allhotels,HttpStatus.OK);
	}

}
