package com.rating.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rating.Entities.Rating;
import com.rating.Exception.ResourceNotFoundException;
import com.rating.Repository.RatingRepository;
import com.rating.Service.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Override
	public Rating createRating(Rating rating) {
		return ratingRepository.save(rating) ;
	}

	@Override
	public Rating getRatingById(String ratingId) {
	Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating Not found with this ID : "+ratingId));
	return  rating;
	}

	@Override
	public Rating updateRating(Rating rating, String ratingId) {
		Rating rating2 = ratingRepository.findById(ratingId).orElseThrow(()->new ResourceNotFoundException("Rating Not found with this ID : "+ratingId));
		rating2.setFeedback(rating.getFeedback());
		rating2.setRating(rating.getRating());
		return ratingRepository.save(rating2);
	}

	@Override
	public void deleteRating(String ratingId) {
		Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new ResourceNotFoundException("Rating Not found with this ID : "+ratingId));
		ratingRepository.delete(rating);
		
	}

	@Override
	public List<Rating> getAllratings() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUserId(String userId) {
		return ratingRepository.findByuserId(userId);
	}

	@Override
	public List<Rating> getRatingByHotelId(String hotelId) {
		return ratingRepository.findByhotelId(hotelId);
	}

}
