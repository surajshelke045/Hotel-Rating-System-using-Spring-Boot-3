package com.rating.Service;

import java.util.List;

import com.rating.Entities.Rating;

public interface RatingService {

	Rating createRating(Rating rating);
	
	Rating getRatingById(String ratingId);
	
	Rating updateRating(Rating rating ,String ratingId);
	
	void deleteRating(String ratingId);
	
	List<Rating> getAllratings();
	
	List<Rating> getRatingByUserId(String userId);
	
	List<Rating> getRatingByHotelId(String HotelId);
	
	
}
