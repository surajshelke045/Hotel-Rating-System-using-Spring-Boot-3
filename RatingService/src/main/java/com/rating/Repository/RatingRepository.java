package com.rating.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.rating.Entities.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

	List<Rating> findByuserId(String userId);
	List<Rating> findByhotelId(String hotelId);
}
