package com.space.service.dao;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipDto;
import com.space.model.ShipType;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ShipDAO {
    ResponseEntity<?> findById(Long id);
    Page<Ship> findAllShips(String name, String planet, ShipType shipType,
                                   Long after, Long before, Boolean isUsed,
                                   Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                   Integer maxCrewSize, Double minRating, Double maxRating,
                                   ShipOrder order, Integer pageNumber, Integer pageSize);
    Integer getCount(String name, String planet, ShipType shipType,
                  Long after, Long before, Boolean isUser,
                  Double minSpeed, Double maxSpeed, Integer minCreeSize,
                  Integer maxCreeSize, Double minRating, Double maxRating);

    ResponseEntity<?> createShip(ShipDto shipDto);

    ResponseEntity<?> updateShip(Long id, ShipDto shipDto);
    ResponseEntity<?> deleteShip(Long id);

}
