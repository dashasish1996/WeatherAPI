package com.infy.weather.application.dao;

import com.infy.weather.application.entity.LocationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDetailsJPA extends JpaRepository<LocationDetails, Integer> {
}
