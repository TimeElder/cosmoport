package com.space.service.dao;

import com.space.controller.ShipOrder;
import com.space.model.Ship;
import com.space.model.ShipDto;
import com.space.model.ShipType;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ShipDAOImpl implements ShipDAO {

    @Autowired
    private ShipService shipService;

    @Override
    public ResponseEntity<Ship> findById(Long id) {
        if (id == 0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<Ship> ship = shipService.findShipById(id);
        return ship.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Page<Ship> findAllShips(String name, String planet, ShipType shipType,
                                   Long after, Long before, Boolean isUsed,
                                   Double minSpeed, Double maxSpeed, Integer minCrewSize,
                                   Integer maxCrewSize, Double minRating, Double maxRating,
                                   ShipOrder order, Integer pageNumber, Integer pageSize ) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        Specification<Ship> spec = ShipSpecifications.collect(name, planet, shipType,  after, before, isUsed, minSpeed, maxSpeed,
                minCrewSize, maxCrewSize, minRating, maxRating);


        return shipService.findAllShips(spec, PageRequest.of(pageNumber, pageSize, Sort.by(order.getFieldName())));
    }

    @Override
    public Integer getCount(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed, Double minSpeed,
                         Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating) {
    Specification<Ship> spec = ShipSpecifications.collect(name, planet, shipType, getTime(after), getTime(before), isUsed, minSpeed, maxSpeed,
            minCrewSize, maxCrewSize, minRating, maxRating);
        return shipService.findAllShips(spec).size();
    }

    private Long getTime(Long time){
        if (time == null)
            return null;
        Date date = new Date(time);
        int y = date.getYear();
        date.setTime(0);
        date.setYear(y);
        date.setDate(0);
        return date.getTime();
    }

    public ResponseEntity<?> createShip(ShipDto shipParams) {
        if (shipParams == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (shipParams.getName() == null || shipParams.getName().isEmpty() || shipParams.getName().length() > 50)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (shipParams.getPlanet() == null || shipParams.getPlanet().isEmpty() || shipParams.getPlanet().length() > 50)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (shipParams.getShipType() == null || shipParams.getShipType().isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (shipParams.getProdDate() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (shipParams.getProdDate() < 0 ||
                shipParams.getProdDate() < new GregorianCalendar(2800, 0, 1).getTimeInMillis() ||
                shipParams.getProdDate() > new GregorianCalendar(3020, 0, 1).getTimeInMillis())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (shipParams.getSpeed() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        shipParams.setSpeed(Math.round(shipParams.getSpeed() * 100) / 100.0);
        if (shipParams.getSpeed() < 0.01 || shipParams.getSpeed() > 0.99)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if (shipParams.getCrewSize() == null || shipParams.getCrewSize() < 1 || shipParams.getCrewSize() > 9999)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Ship ship = new Ship(shipParams);
        shipService.addShip(ship);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> updateShip(Long id, ShipDto shipDto) {
        ResponseEntity<Ship> re = findById(id);
        if (re.getStatusCode().isError())
            return re;
        Ship ship = re.getBody();
        if (shipDto != null)
            re = ship.update(shipDto);
        if (re.getStatusCode().isError())
            return re;
        shipService.updateShip(ship);
        return new ResponseEntity<>(ship, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteShip(Long id) {
        ResponseEntity re =  findById(id);
        if (re.getStatusCode().isError())
            return re;
        shipService.deleteShip(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
