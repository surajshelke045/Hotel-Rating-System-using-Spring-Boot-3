package com.user.ServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.Entities.Hotel;
import com.user.Entities.Rating;
import com.user.Entities.User;
import com.user.Exception.ResourceNotFoundException;
import com.user.Repository.UserRepository;
import com.user.Service.UserService;
import com.user.external.services.HotelService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;
	
	private Logger logger=LoggerFactory.getLogger(UserServiceImpl.class);

	
	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
	   return this.userRepository.save(user);
		
	}

	@Override
	public List<User> getAlluser() {
		
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
	User user=	userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given ID is not found on server !! UserId : "+ userId));
    // fetch ratings from rating service
	Rating[] ratingsofUser=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
    logger.info("{} ",ratingsofUser);
    System.out.println(ratingsofUser);
    List<Rating> list = Arrays.stream(ratingsofUser).toList();
    List<Rating> ratingList=list.stream().map(rating -> {
//    	ResponseEntity<Hotel> entity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
    	Hotel hotel = hotelService.getHotel(rating.getHotelId());
//    	logger.info("Response status code : {} ",entity.getStatusCode());
    	rating.setHotel(hotel);
    	return rating;
    }).collect(Collectors.toList());
    user.setRatings(ratingList);
	return user;
		
	}

	@Override
	public User updateUser(User user, String userId) {
	User user1 = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given ID is not found on server !! UserId : "+ userId));
	user1.setName(user.getName());
	user1.setEmail(user.getEmail());
	user1.setAbout(user.getAbout());
    return userRepository.save(user1);
   
	}

	@Override
	public void deleteUser(String userId) {

	User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User with given ID is not found on server !! UserId : "+ userId));
		
	 userRepository.delete(user);
	}

}
