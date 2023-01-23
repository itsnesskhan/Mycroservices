package com.hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.models.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

	List<Hotel> findByLocation(String location);
}
