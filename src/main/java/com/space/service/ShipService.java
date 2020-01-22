package com.space.service;

import com.space.model.Ship;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface ShipService {
    Optional<Ship> findShipById(Long id);
    List<Ship> findAllShips();
    Page<Ship> findAllShips(Specification<Ship> spec, Pageable page);
    List<Ship> findAllShips(Specification<Ship> spec);
    Long getCount();
    List<Ship> findAllShips(Sort sort);
    List<Ship> findAllShips(Example<Ship> example, Sort sort);
    void addShip(Ship ship);
    void updateShip(Ship ship);
    void deleteShip(Long id);

}
