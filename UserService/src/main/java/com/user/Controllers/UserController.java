package com.user.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.Entities.User;
import com.user.Payload.ApiResponse;
import com.user.Service.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User saveUser = userService.saveUser(user);
		return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/{userId}")
 //	 @CircuitBreaker(name = "ratingHotelBreaker",fallbackMethod ="ratingHotelFallback" )
//  @Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
  @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId)
	{
		 logger.info("Get Single User Handler: UserController");
		User user = userService.getUser(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
		
	}
	
	public ResponseEntity<User> ratingHotelFallback(String userId,Exception ex){

        ex.printStackTrace();

        User user = User.builder()
        		.email("dummy@gmail.com")
        		.name("Dummy")
        		.about("This user is created dummy because some service is down")
        		.userId("141234")
        		.build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUsers(){
		List<User> alluser = userService.getAlluser();
		return new ResponseEntity<List<User>>(alluser,HttpStatus.OK);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<User> updateUser(@RequestBody User user,@PathVariable String userId)
	{
		User updateUser = userService.updateUser(user,userId);
        return new ResponseEntity<User>(updateUser,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable String userId)
	{
		userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse( "User Deleted Successfully..!",true,HttpStatus.OK), HttpStatus.OK);
		
	}

}
