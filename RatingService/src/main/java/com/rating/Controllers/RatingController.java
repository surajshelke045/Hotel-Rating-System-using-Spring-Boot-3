package com.rating.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rating.Entities.Rating;
import com.rating.Service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/")
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
		Rating createRating = ratingService.createRating(rating);
		return new ResponseEntity<Rating>(createRating,HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings(){
		List<Rating> allratings = ratingService.getAllratings();
		return new ResponseEntity<List<Rating>>(allratings,HttpStatus.OK);
		
	}
	
	@PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
	@GetMapping("/users/{userId}")
	public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
		List<Rating> ratings = ratingService.getRatingByUserId(userId);
		return new ResponseEntity<List<Rating>>(ratings,HttpStatus.OK);
		
	}
	
	@GetMapping("/hotels/{hotelId}")
	public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
		List<Rating> ratings = ratingService.getRatingByHotelId(hotelId);
		return new ResponseEntity<List<Rating>>(ratings,HttpStatus.OK);
		
	}

}
