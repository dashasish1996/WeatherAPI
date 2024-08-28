package com.infy.weather.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "WEATHER_API_LOCATION_DETAILS")
public class LocationDetails {
    @Id
    @Column(name = "LOCATION_ID")
    private Integer locationId;

    @Column(name = "LOCATION_NAME")
    private String locationName;

    @Column(name = "QUERY_PARAMETER")
    private String  queryParameter;
}
