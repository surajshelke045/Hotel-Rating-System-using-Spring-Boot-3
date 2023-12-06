package com.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotel.Entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,String> {

}
